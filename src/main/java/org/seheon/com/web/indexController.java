package org.seheon.com.web;

import lombok.RequiredArgsConstructor;
import org.seheon.com.service.posts.PostsService;
import org.seheon.com.web.dto.PostsResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
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
