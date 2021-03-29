package cn.wsm.fruit.controller;


import cn.wsm.fruit.handler.MyExceptionHandler;
import cn.wsm.fruit.pojo.Level;
import cn.wsm.fruit.service.LevelService;
import cn.wsm.fruit.utils.UserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LevelController {

    @Autowired
    private LevelService levelService;


     @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/start-level")
    public String startLevel(@RequestParam(defaultValue = "1") Integer lv){
        Level level = levelService.getLevel(lv);
        StringBuilder sb = new StringBuilder();
        String uid = UserIdUtil.getUid();
        Level newLevel = level.getNewLevel(uid);
        levelService.addLevelForUser(newLevel);
        sb.append(uid);
        sb.append("\n");
        return getResponse(sb,level);
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/move")
    public String move(@RequestParam(required = true)String sessionId,@RequestParam(required = true)Integer row0,@RequestParam(required = true)Integer col0,@RequestParam(required = true)Integer row1,@RequestParam(required = true)Integer col1){
        Level levelByUid = levelService.getLevelByUid(sessionId);
        if(null == levelByUid){
            return MyExceptionHandler.ERROR_MESSAGE;
        }
        if(row0<0 || row0>7 || col0<0 || col0> 7 || row1<0 || row1>7 || col1<0 || col1> 7){
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        Level move = levelService.move(levelByUid, row0, col0, row1, col1);
        return getResponse(sb,move);

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String test(@RequestParam(defaultValue = "1") Integer lv){

        Level level = levelService.getLevel(1);
        StringBuilder sb = new StringBuilder();
        Level move = levelService.move(level, 1, 0, 4, 1);
        return getResponse(sb,move);

    }


    private String getResponse(StringBuilder sb,Level lv){
        sb.append(lv.getOne());
        sb.append("\n");
        sb.append(lv.getTwo());
        sb.append("\n");
        sb.append(lv.getThree());
        sb.append("\n");
        sb.append(lv.getFour());
        sb.append("\n");
        sb.append(lv.getFive());
        sb.append("\n");
        sb.append(lv.getSix());
        sb.append("\n");
        sb.append(lv.getSeven());
        sb.append("\n");
        sb.append(lv.getEight());
        return sb.toString();
    }



}
