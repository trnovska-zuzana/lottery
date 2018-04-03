package lottery.frontend.controllers;

import lottery.backend.services.LotteryService;
import lottery.backend.constants.Messages;
import lottery.entities.Statistics;
import lottery.entities.Ticket;
import lottery.frontend.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class LotteryController {

    @Autowired
    LotteryService lotteryService;

    private static final String NEW_TICKET_VIEW = "newTicket";

    @GetMapping("/newTicket")
    public String newTicket(Ticket ticket) {
        return NEW_TICKET_VIEW;
    }

    @PostMapping("/newTicket")
    public String saveTicket(@Valid Ticket ticket, BindingResult br, Model model) {

        MessageBox messageBox = new MessageBox();

        if (lotteryService.validate(ticket) == false) {
            messageBox.getError().add(Messages.NUMBERS_NOT_UNIQUE);
            model.addAttribute("messageBox", messageBox);
            return NEW_TICKET_VIEW;
        }
        if (br.hasErrors()) {
            return NEW_TICKET_VIEW;
        }
        lotteryService.startLottery(ticket);
        messageBox.getSuccess().add(Messages.LOTTERY_STARTED);
        model.addAttribute("messageBox", messageBox);

        return NEW_TICKET_VIEW;
    }

    @GetMapping("/statistics")
    public String showStatistics(@RequestParam(value = "playerName", required = false) String playerName, Model model) {

        Iterable<Statistics> statisticsList;

        if (playerName != null) {
            statisticsList = lotteryService.getStatisticsByPlayer(playerName);
        } else {
            statisticsList = lotteryService.getStatistics();
        }
        model.addAttribute("statisticsList", statisticsList);

        return "statistics";
    }
}
