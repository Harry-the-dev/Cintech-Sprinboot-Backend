package com.cintech.PriceJuxtapose.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StringF {
    char ch = '-';
    int t = 65;
    int v = 10;
    int s = 18 ;

    DecimalFormat df = new DecimalFormat("#.00");

    int no = 4;

    private  static  String L (String str, int L) {
        String result = StringUtils.leftPad(str, L);
        return result;
    }

    private  static   String C (String str, int L) {
        String result = StringUtils.center(str, L);
        return result;
    }
    private  static   String C2 (String str, int L, char x) {
        String result = StringUtils.center(str, L,x);
        return result;
    }

    private  static   String R (String str, int L) {
        String result = StringUtils.rightPad(str, L);
        return result;
    }
    private  static   String R2 (String str, int L, char x) {
        String result = StringUtils.rightPad(str, L,x);
        return result;
    }




    public String shortDesc (String txt)
    {
        return  "\n"+ C2("",no+v + t+s ,'=')+"\n"+C2(txt,no+v + t+s ,'-') +"\n"+ C2("",no+v + t+s ,'=');
    }
    public String longDesc (String txt)
    {
        return "\n"+ C2("",no+v + t+s+s ,'=')+"\n"+C2(txt,no+v + t+s+s ,'-') +"\n"+ C2("",no+v + t+s+s ,'=');
    }



    public String UserProduct (int count,String vitality , String prod, String p){
        return R(String.valueOf(count),no)+C(vitality,v)+ R(prod,t)+ R('R'+p,s);
    }
    public String MainProduct (int count,String vitality , String prod , String w , String p){
        return R(String.valueOf(count),no)+C(vitality,v)+ R(prod,t)+ R('R'+w,s)+ R('R'+p,s);
    }



    public String MainHeader (){
        return C(" WOOLWORTHS + PICK N PAY ",no+v + t+s+s )+"\n"+R("#",no)+R("Vitality",v)+ R("Product List",t)+ R("Woolworths Price",s)+ R("Pick N Pay Price",s);
    }

    public String PnpHeader () {
        return  C2(" PICK N PAY ",no+v + t+s,'-' )+"\n"+ R("#",no)+R("Vitality",v)+ R("Product List",t)+ R("Pick N Pay Price",s);
    }
    public String WoolHeader () {
        return  C2(" WOOLWORTHS ",no+v + t+s ,'-')+"\n"+R("#",no)+R("Vitality",v)+ R("Product List",t)+ R("Woolworths Price",s);
    }




    public String MainSummary (double wool , double pnp ){
        return R2("",no+v+t+s+s,'-') + "\n" +C ("Total from Each Store",no+v+t)+ R('R'+String.valueOf(df.format(wool)),s) +  R('R'+String.valueOf((df.format(pnp))),s)+"\n"+R2("",no+v+t+s+s,'-');
    }
    public String UserSummary ( double pnp ){
        return R2("",no+v+t+s,'-') + "\n" +C ("Total",no+v+t)+  R('R'+String.valueOf((df.format(pnp))),s)+"\n"+R2("",no+v+t+s,'-') ;
    }
    public String GrandTotal ( double total ){
       return  R2("",no+v+t+s,'-') + "\n" +C ("Total Spend ",no+v+t)+  R('R'+String.valueOf((df.format(total))),s)+"\n"+R2("",no+v+t+s,'-');
    }


}
