package se.mirado.jgs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.vavr.control.Try;
import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.common.Query;
import se.mirado.jgs.data.Done;
import se.mirado.jgs.data.time.CalFactory;
import se.mirado.jgs.data.time.CalRenderer;
import se.mirado.jgs.data.time.SimpleDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Component
@Controller
public class Index {

    private final AppReactor appReactor;
    private final CalFactory calFactory;

    @Autowired
    public Index(AppReactor appReactor) {
        this.appReactor = appReactor;
        this.calFactory = new CalFactory(Locale.getDefault());
    }

    @RequestMapping("/")
    public String index(Model model) {

        LocalDate currentDate = LocalDate.now();
        SimpleDate targetDate = SimpleDate.today();

        return page(model, currentDate, targetDate);
    }

    public String page(Model model, LocalDate currentDate, SimpleDate targetDate) {
        return appReactor.query(queryDonesForDate(targetDate))
                .map(dones -> pageSuccess(model, currentDate, targetDate, dones))
                .getOrElseGet(ex -> pageFailure(model, ex));
    }

    private String pageSuccess(Model model, LocalDate currentDate, SimpleDate targetDate, List<Done> dones) {
        model.addAttribute("date", targetDate);
        model.addAttribute("username", Security.getEscapedUserName());
        model.addAttribute("dones", dones);
        model.addAttribute("calendar", CalRenderer.render(calFactory.fromLocaleDate(currentDate)));
        model.addAttribute("success", true);
        return "index";
    }

    private static String pageFailure(Model model, Throwable t) {
        model.addAttribute("success", false);
        return "failure";
    }

    private static Query<List<Done>> queryDonesForDate(SimpleDate targetDate) {
        return Query.named(
                "Reading dones for date " + targetDate.toString(),
                as -> Try.of(() -> as
                        .getDones()
                        .filter(d -> d._2().getDate().equals(targetDate))
                        .values()
                        .toJavaList()));
    }

}
