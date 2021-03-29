package cn.wsm.fruit.service.impl;

import cn.wsm.fruit.dao.LevelRepository;
import cn.wsm.fruit.pojo.Level;
import cn.wsm.fruit.service.LevelService;
import cn.wsm.fruit.utils.Fruit;
import cn.wsm.fruit.utils.FruitUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public Level getLevel(Integer level) {
        Level levelById = levelRepository.findLevelByLv(level);
        return levelById;
    }

    /**
     *  使用时需要注意  在controller 层判断是否设置 level 的主键
     *  该方法会判断是否有主建 ID 如果有 id 就是修改操作 如果没有就是更新操作
     *
     *
     * @param level
     * @param
     */
    @Override
    public void addLevelForUser(Level level) {
        levelRepository.save(level);
    }

    // 查询用户id 的数据
    @Override
    public Level getLevelByUid(String uId) {
        Level levelByUid = levelRepository.findLevelByUid(uId);
        return levelByUid;
    }


    /**
     *
     *  执行消除的方法
     *  （B普通果冻，H横炸弹果冻，V竖炸弹果冻，S⽅方炸弹果冻）
     * @param level
     * @param row0
     * @param col0
     * @param row1
     * @param col1
     * @return
     */
    @Override
    public Level move(Level level, Integer row0, Integer col0, Integer row1, Integer col1) {
        String[][] allFruit = new String[8][8];
        allFruit[0] = FruitUtil.getFruits(level.getOne());
        allFruit[1] = FruitUtil.getFruits(level.getTwo());
        allFruit[2] = FruitUtil.getFruits(level.getThree());
        allFruit[3] = FruitUtil.getFruits(level.getFour());
        allFruit[4] = FruitUtil.getFruits(level.getFive());
        allFruit[5] = FruitUtil.getFruits(level.getSix());
        allFruit[6] = FruitUtil.getFruits(level.getSeven());
        allFruit[7] = FruitUtil.getFruits(level.getEight());

        log.info("===>1 "+ JSON.toJSONString(allFruit));
        // i表示y轴  ， j表示x轴
        for(int i =0;i<allFruit.length;i++){
            for(int j=0;j<allFruit.length;j++){
                if(i>=col0&& i<=col1 && j>=row0&& j<=row1){
                    doEliminate(j,i,allFruit[j][i],allFruit);
                }
            }
        }

        log.info("===>2 "+ JSON.toJSONString(allFruit));
        doMove(allFruit);

        log.info("===>3 "+ JSON.toJSONString(allFruit));

        level.setOne(FruitUtil.getFruits(allFruit[0]));
        level.setTwo(FruitUtil.getFruits(allFruit[1]));
        level.setThree(FruitUtil.getFruits(allFruit[2]));
        level.setFour(FruitUtil.getFruits(allFruit[3]));
        level.setFive(FruitUtil.getFruits(allFruit[4]));
        level.setSix(FruitUtil.getFruits(allFruit[5]));
        level.setSeven(FruitUtil.getFruits(allFruit[6]));
        level.setEight(FruitUtil.getFruits(allFruit[7]));
        levelRepository.save(level);
        return level;
    }

    /**
     *  （B普通果冻，H横炸弹果冻，V竖炸弹果冻，S⽅方炸弹果冻）
     *
     *  row-> y 轴   col-> x轴
     *
     * @param x
     * @param y
     * @param fruitType
     */
    private String[][] doEliminate(Integer x,Integer y,String fruitType,String[][] allFruit){
        // 行消除
        allFruit[x][y] = Fruit.O;
        if(fruitType.equals(Fruit.B)){
            return allFruit;
        }
        if(fruitType.equals(Fruit.O)){
            return allFruit;
        }

        if(fruitType.equals(Fruit.H)){
            for(int i=0;i<allFruit[x].length;i++){
                String h = allFruit[x][i];
                doEliminate(x,i,h,allFruit);
            }
        }
        // 列消除
        if(fruitType.equals(Fruit.V)){
            for(int i=0;i<allFruit.length;i++){
                String v = allFruit[i][y];
                doEliminate(i,y,v,allFruit);
            }
        }

        if(fruitType.equals(Fruit.S)){

            int xx = (x-1)<0?0:(x-1);
            int yy = (y-1)<0?0:(y-1);

            int xx2 = (x+1)>7?7:(x+1);
            int yy2 = (y+1)>7?7:(y+1);

           String a1 = allFruit[xx2][y];
            doEliminate(xx2,y,a1,allFruit);


           String a2 = allFruit[xx][y];
            doEliminate(xx,y,a2,allFruit);


           String a3 = allFruit[x][yy2];
            doEliminate(x,yy2,a3,allFruit);


           String a4 = allFruit[x][yy];
            doEliminate(x,yy,a4,allFruit);


           String a5 = allFruit[xx2][yy2];
            doEliminate(xx2,yy2,a5,allFruit);



           String a6 = allFruit[xx][yy2];
            doEliminate(xx,yy2,a6,allFruit);



           String a7 = allFruit[xx2][yy];
            doEliminate(xx2,yy,a7,allFruit);


           String a8 = allFruit[xx][yy];
           doEliminate(xx,yy,a8,allFruit);

        }

        return allFruit;
    }


   private void doMove(String[][] allFruit){

        for(int i =0;i<8;i++){
            int b = -1;
            for(int j=7;j>=0;j--){
                if(allFruit[j][i].equals(Fruit.O)){
                    if(b==-1){
                        b =j;
                    }
                }else {
                    if(b>0){
                        allFruit[b][i] = allFruit[j][i];
                        allFruit[j][i] = Fruit.O;
                        b--;
                    }
                }
            }
            while (b>=0){
                String s = FruitUtil.randomFruit();
                allFruit[b][i] = s;
                b--;
            }

        }


    }



}
