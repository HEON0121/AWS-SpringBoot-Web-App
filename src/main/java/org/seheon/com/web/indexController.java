package org.seheon.com.web;

import lombok.RequiredArgsConstructor;
import org.seheon.com.config.auth.LoginUser;
import org.seheon.com.config.auth.dto.SessionUser;
import org.seheon.com.domain.users.User;
import org.seheon.com.service.posts.PostsService;
import org.seheon.com.web.dto.PostsResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String savePosting() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePosting(@PathVariable Long id, Model model) {
        PostsResponseDTO dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
