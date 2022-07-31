package com.cintech.PriceJuxtapose.service;


import com.cintech.PriceJuxtapose.DTO.GroceryListDTO;
import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.entity.NotificationEmail;
import com.cintech.PriceJuxtapose.entity.PickNPay;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.entity.Woolworth;
import com.cintech.PriceJuxtapose.helper.StringF;
import com.cintech.PriceJuxtapose.helper.StringBuilderPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComparisonService {

    @Autowired
    private MainService mainService;

    @Autowired
    private MailService mailService;
    @Autowired
    private PickNPayService pickNPayService;

    @Autowired
    private WoolworthsService woolworthsService;


    public void processGroceryList(List<GroceryListDTO> groceryListDTOS, String email) {
        List<PickNPay> pickNPayList = pickNPayFill(groceryListDTOS);
        List<Woolworth> woolworthsList = woolworthsFill(groceryListDTOS);
        List<MainDTO> productList = productFill(pickNPayList, woolworthsList);
        StringBuilderPlus message = new StringBuilderPlus();

        message.append( printListFromAllStores(productList));
        message.append( printUserList(pickNPayList,woolworthsList," USER GENERATED LIST "));
        message.append(printOptimizeForPrice(productList));

        System.out.println(message.toString());
        System.out.println("===================================================================//////////////////////");

        mailService.sendMail(new NotificationEmail("PriceJuxtapose Grocery List",email,message.toString()));


    }

    public String printUserList(List<PickNPay> pickNPayList, List<Woolworth> woolworthsList, String txt)
    {

        int count = 0 ;
        String vit = "";
        double woolTotal = 0 ;
        double pnpTotal = 0 ;
        StringBuilderPlus sb = new StringBuilderPlus();
        StringF str =  new StringF();

       sb.appendLine( str.shortDesc("  " +txt +"  "));

        if (pickNPayList.size() > 0) {
            sb.appendLine(str.PnpHeader());
            for (PickNPay item : pickNPayList) {
                count += 1;
                String pnpPrice = item.getPrice().toString();
                pnpTotal += item.getPrice();
                if (item.getVitality()) {vit = "*";}
                String title = item.getProduct().getProdTitle();
                sb.appendLine(str.UserProduct(count, vit, title, pnpPrice));
            }
            sb.appendLine(str.UserSummary(pnpTotal));
        }

        if ( woolworthsList.size() > 0 ) {
            vit = "";
            count = 0;
            sb.appendLine(str.WoolHeader());
            for (Woolworth item : woolworthsList) {
                count += 1;
                String woolPrice = item.getPrice().toString();
                woolTotal += item.getPrice();
                if (item.getVitality()) {vit = "*";}
                String title = item.getProduct().getProdTitle();
                sb.appendLine(str.UserProduct(count, vit, title, woolPrice));
            }
            sb.appendLine(str.UserSummary(woolTotal));
        }
        sb.appendLine(str.GrandTotal(woolTotal+pnpTotal));
        //System.out.println(sb.toString());

        return sb.toString();
    }




    public String printListFromAllStores (List<MainDTO> productList)
    {

        int count = 0 ;
        String vit = "";
        double woolTotal = 0 ;
        double pnpTotal = 0 ;
        StringBuilderPlus sb = new StringBuilderPlus();
        StringF str =  new StringF();
        sb.appendLine( str.longDesc(" COMPREHENSIVE lIST "));
        sb.appendLine(str.MainHeader());
        for (MainDTO item : productList)
        {
            count +=1 ;
            woolTotal += item.getWoolworths().getPrice();
            pnpTotal += item.getPickNPay().getPrice();

            if (item.getPickNPay().getVitality() && item.getWoolworths().getVitality()){ vit = "*";}
            String title = item.getProduct().getProdTitle();
            String woolPrice = item.getWoolworths().getPrice().toString();
            String pnpPrice = item.getPickNPay().getPrice().toString();
            sb.appendLine(str.MainProduct(count,vit,title,woolPrice,pnpPrice));

        }
        sb.appendLine(str.MainSummary(woolTotal,pnpTotal));
        //System.out.println(sb.toString());
        return sb.toString();
    }


    public String printOptimizeForPrice (List<MainDTO> productList)
    {
        List<Woolworth> woolworthsList = new ArrayList<>();
        List<PickNPay> pickNPayList = new ArrayList<>();

        for (MainDTO item : productList)
        {
            if ( item.getPickNPay().getPrice() >  item.getWoolworths().getPrice()) {
                woolworthsList.add(item.getWoolworths());
            }
            else if (item.getPickNPay().getPrice() <=  item.getWoolworths().getPrice()) {
                pickNPayList.add(item.getPickNPay());
            }
        }

        return printUserList(pickNPayList, woolworthsList, " PRICE OPTIMIZED LIST ");
    }





    public List<MainDTO> productFill (List<PickNPay> pickNPayList, List<Woolworth> woolworthsList )
    {


        List<MainDTO> productList = new ArrayList<>();

        for ( Woolworth item : woolworthsList ) {
            Product productItem = item.getProduct();
            if ( !compareProduct(productItem,productList) ){
                productList.add(mainService.assignToMainDTO(productItem));
            }
        }

        for ( PickNPay item : pickNPayList ) {
            Product productItem = item.getProduct();
            if ( !compareProduct(productItem,productList)){
                productList.add(mainService.assignToMainDTO(productItem));
            }
        }


        return productList;
    }
    public List<PickNPay> pickNPayFill (List<GroceryListDTO> groceryListDTOS) {
        List<PickNPay> pickNPayList = new ArrayList<>();
        for (GroceryListDTO item : groceryListDTOS) {
                PickNPay pickNPayProduct = pickNPayService.getPickNpayByUrl(item.getUrl());
                if (pickNPayProduct != null && !comparePickNPay(pickNPayProduct, pickNPayList)) {
                    pickNPayList.add(pickNPayProduct);}
        }
        return pickNPayList;
    }

    public List<Woolworth> woolworthsFill (List<GroceryListDTO> groceryListDTOS) {
        List<Woolworth> woolworthsList = new ArrayList<>();
        for (GroceryListDTO item : groceryListDTOS) {
            System.out.println(item.getUrl());
                Woolworth woolworthsProduct = woolworthsService.getWoolworthByUrl(item.getUrl());
                if (woolworthsProduct != null && !compareWoolworths(woolworthsProduct, woolworthsList)) {
                    woolworthsList.add(woolworthsProduct);
                }
        }
        return woolworthsList;
    }

    public Boolean compareWoolworths (Woolworth one , List<Woolworth> list) {
        for (Woolworth item : list){
            if ( item.equals(one)){
                return true ;
            }
        }
        return  false;
    }
    public Boolean comparePickNPay (PickNPay one , List<PickNPay> list) {
        for (PickNPay item : list){
            if ( item.equals(one)){
                return true ;
            }
        }
        return  false;
    }

    public Boolean compareProduct (Product one , List<MainDTO> list) {
        for (MainDTO item : list){
            if ( item.getProduct().equals(one)){
                return true ;
            }
        }
        return  false;
    }




}
