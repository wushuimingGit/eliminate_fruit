package cn.wsm.fruit.utils;

import java.util.Random;

public class FruitUtil {

    private static final Random rand = new Random();

    public static String[] produceFruit(Integer count){
        if(null ==count) count =1;
        String[] fruits = new String[count];
        for(int i =0;i<count;i++){
            fruits[i] = randomFruit();
        }
        return fruits;
    }


    // 当前生成每个水果的概率 是相等的
    // 近可能多的生产 普通水果
    // 可以通过 netInt方法参数 bound 简单的控制 难易程度
    public static String randomFruit(){
        int i = rand.nextInt(4)+1;
        if(i<=1){
            return Fruit.B;
        }
        if(i==2){
            return Fruit.H;
        }
        if(i==3){
            return Fruit.V;
        }
        if(i==4){
            return Fruit.S;
        }
        return Fruit.B;
    }

    /**
     *  将单行的水果转成 一个数组
     *
     * @param fruit
     * @return
     */
    public static String[] getFruits(String fruit){
        String[] stringArray = new String[fruit.length()];
        for(int i = 0;i<fruit.length();i++){
            stringArray[i] = fruit.substring(i,i+1);
        }
        return stringArray;

    }
    /**
     *  将单行的水果转成 一个数组
     *
     * @param
     * @return
     */
    public static String getFruits(String[] fruits){
        String s = "";
        for(int i = 0;i<fruits.length;i++){
            s = s+fruits[i];
        }
        return s;

    }



    public static void main(String[] args) throws InterruptedException {
        String[] s= getFruits("SBBBBSBB");
        System.out.println(s);
    }





    /**
     *  （B普通果冻，H横炸弹果冻，V竖炸弹果冻，S⽅方炸弹果冻）
     *
     * @param row0
     * @param col0
     * @param fruitType
     */
    private String[][] doMoveBak(Integer row0,Integer col0,String fruitType,String[][] allFruit){
        // 行消除

        if(fruitType.equals(Fruit.B)){
            allFruit[col0][row0] = Fruit.O;
            return allFruit;
        }
        if(fruitType.equals(Fruit.O)){
            return allFruit;
        }

        if(fruitType.equals(Fruit.H)){
            for(int i=0;i<allFruit[col0].length;i++){
                String h = allFruit[row0][i];
                doMoveBak(row0,i,h,allFruit);
            }
        }
        // 列消除
        if(fruitType.equals(Fruit.V)){
            for(int i=0;i<allFruit.length;i++){
                String v = allFruit[i][col0];
                doMoveBak(i,col0,v,allFruit);
            }
        }

        if(fruitType.equals(Fruit.S)){
            allFruit[col0][row0] = Fruit.O;

            String a1 = allFruit[col0][row0+1];
            if(a1.equals(Fruit.B)){
                allFruit[col0][row0+1] = Fruit.O;
            }
            if(a1.equals(Fruit.S) || a1.equals(Fruit.V) || a1.equals(Fruit.H)){
                doMoveBak(row0+1,col0,a1,allFruit);
            }

            String a2 = allFruit[col0][row0-1];
            if(a2.equals(Fruit.B)){
                allFruit[col0][row0-1] = Fruit.O;
            }
            if(a2.equals(Fruit.S) || a2.equals(Fruit.V) || a2.equals(Fruit.H)){
                doMoveBak(row0-1,col0,a1,allFruit);
            }

            String a3 = allFruit[col0+1][row0];
            if(a3.equals(Fruit.B)){
                allFruit[col0+1][row0] = Fruit.O;
            }
            if(a3.equals(Fruit.S) || a3.equals(Fruit.V) || a3.equals(Fruit.H)){
                doMoveBak(row0,col0+1,a1,allFruit);
            }

            String a4 = allFruit[col0-1][row0];
            if(a4.equals(Fruit.B)){
                allFruit[col0-1][row0] = Fruit.O;
            }
            if(a4.equals(Fruit.S) || a4.equals(Fruit.V) || a4.equals(Fruit.H)){
                doMoveBak(row0,col0-1,a1,allFruit);
            }

            String a5 = allFruit[col0+1][row0+1];
            if(a5.equals(Fruit.B)){
                allFruit[col0+1][row0+1] = Fruit.O;
            }
            if(a5.equals(Fruit.S) || a5.equals(Fruit.V) || a5.equals(Fruit.H)){
                doMoveBak(col0+1,row0+1,a1,allFruit);
            }


            String a6 = allFruit[col0+1][row0-1];
            if(a6.equals(Fruit.B)){
                allFruit[col0+1][row0-1] = Fruit.O;
            }
            if(a6.equals(Fruit.S) || a6.equals(Fruit.V) || a6.equals(Fruit.H)){
                doMoveBak(col0+1,row0-1,a1,allFruit);
            }


            String a7 = allFruit[col0-1][row0+1];
            if(a7.equals(Fruit.B)){
                allFruit[col0+1][row0-1] = Fruit.O;
            }
            if(a7.equals(Fruit.S) || a7.equals(Fruit.V) || a7.equals(Fruit.H)){
                doMoveBak(col0+1,row0-1,a1,allFruit);
            }


            String a8 = allFruit[col0-1][row0-1];
            if(a8.equals(Fruit.B)){
                allFruit[col0-1][row0-1] = Fruit.O;
            }
            if(a8.equals(Fruit.S) || a8.equals(Fruit.V) || a8.equals(Fruit.H)){
                doMoveBak(col0-1,row0-1,a1,allFruit);
            }
        }



        return allFruit;
    }



}
