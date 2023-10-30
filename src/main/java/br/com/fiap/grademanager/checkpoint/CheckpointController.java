package br.com.fiap.grademanager.checkpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/checkpoint")
public class CheckpointController {

    @Autowired
    CheckpointService service;

    @Autowired
    MessageSource messages;

    @GetMapping 
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("checkpoints", service.findAll());
        return "checkpoint/index";
    }

    @GetMapping("new")
    public String form(Checkpoint checkpoint){
        return "checkpoint/form";
    }

    @PostMapping
    public String create(@Valid Checkpoint checkpoint, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "checkpoint/form";

        service.save(checkpoint);
        redirect.addFlashAttribute("success", "Checkpoint cadastrado com sucesso");
        return "redirect:/checkpoint";
    }


    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", getMessage("checkpoint.delete.success") );
        }else{
            redirect.addFlashAttribute("error", getMessage("checkpoint.notfound")) ;
        }
        return "redirect:/checkpoint";
    }

    private String getMessage(String code){
        return messages.getMessage(code, null, LocaleContextHolder.getLocale());
    }
    
}
