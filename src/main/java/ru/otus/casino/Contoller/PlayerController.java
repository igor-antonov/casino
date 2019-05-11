package ru.otus.casino.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.casino.domain.Player;
import ru.otus.casino.exception.DataNotFoundException;
import ru.otus.casino.repository.PlayerRepository;

import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("players", playerRepository.findAll());
        return "all";
    }

    @GetMapping("/go")
    public String go(){
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name, @RequestParam("bet") String bet){
        playerRepository.save(new Player(name, bet));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        playerRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        try {
            Player player = playerOptional.orElseThrow(DataNotFoundException::new);
            if (player.getBet().equals("black")){
                player.setBet("red");
            }
            else {
                player.setBet("black");
            }
            playerRepository.save(player);
            return "redirect:/";
        }
        catch (DataNotFoundException e){
                model.addAttribute("message", e.getMessage());
                return "error";
        }
    }
}
