package obanyan.dbnyans.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import obanyan.dbnyans.model.Chamber;
import obanyan.dbnyans.model.ChamberMapper;
import obanyan.dbnyans.model.ChamberUser;
//import obanyan.dbnyans.model.UserInfo;

@Controller
@RequestMapping("/sample4")
public class Sample41Controller {

  @Autowired
  ChamberMapper chamberMapper;

  @GetMapping("step1")
  public String sample41() {
    return "sample41.html";
  }

  /**
   * 指定したidをPATHパラメータで受け取り，そのidに対応するデータを取得して返す
   *
   * @param id
   * @param model
   * @return
   */
  @GetMapping("step2/{id}")
  public String sample42(@PathVariable Integer id, ModelMap model) {
    Chamber chamber2 = chamberMapper.selectById(id);
    model.addAttribute("chamber2", chamber2);

    return "sample41.html";
  }

  @GetMapping("step3")
  public String sample43() {
    return "sample43.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   *
   *         Transactionalはメソッドでトランザクション処理を実施したい場合に付与する
   *         このメソッドが開始するとトランザクションが開始され，メソッドが正常に終了するとDBへのアクセスが確定する（Runtime
   *         errorなどで止まった場合はロールバックが行われる）
   */
  @PostMapping("step3")
  @Transactional
  public String sample43(@RequestParam String chamberName, ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    Chamber chamber3 = new Chamber();
    chamber3.setChamberName(chamberName);
    chamber3.setUserName(loginUser);
    chamberMapper.insertChamber(chamber3);
    model.addAttribute("chamber3", chamber3);
    // System.out.println("ID:" + chamber3.getId());
    return "sample43.html";
  }

  @GetMapping("step5")
  public String sample45() {
    return "sample45.html";
  }

  @PostMapping("step5")
  public String sample45(@RequestParam String chamberName, ModelMap model) {
    ArrayList<Chamber> chambers5 = chamberMapper.selectAllByChamberName(chamberName);
    model.addAttribute("chambers5", chambers5);
    return "sample45.html";
  }

  @GetMapping("step6")
  public String sample46() {
    return "sample46.html";
  }

  @GetMapping("step7")
  @Transactional
  public String sample47(ModelMap model) {
    ArrayList<ChamberUser> chamberUsers7 = chamberMapper.selectAllChamberUser();
    model.addAttribute("chamberUsers7", chamberUsers7);
    return "sample46.html";
  }
}
