package com.csc340.security;

import com.csc340.provider.Provider;
import com.csc340.provider.ProviderService;
import com.csc340.review.Review;
import com.csc340.review.ReviewId;
import com.csc340.user.User;
import com.csc340.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.csc340.review.ReviewService;

import java.util.List;
import java.util.Optional;


@Controller
public class SecurityController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReviewService reviewService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/serviceproviderlist")
    public String serviceproviderlist() {
        return "serviceproviderlist";
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        int flaggedCount = reviewService.flaggedCount();
        model.addAttribute("flaggedCount", flaggedCount);
        return "profile";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }

    @GetMapping("/userlist")
    public String adminusers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return "redirect:/userlist";
    }

    @PostMapping("/changeUserRole")
    public String changeUserRole(@RequestParam String username, @RequestParam String userRole, RedirectAttributes redirectAttributes) {
        userService.changeUserRole(username, userRole);
        redirectAttributes.addFlashAttribute("message", "User role updated successfully!");
        return "redirect:/userlist";
    }

    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("username") String username, @RequestParam("providerName") String providerName) {
        User user = userService.findByUsername(username);
        Provider provider = providerService.findByName(providerName);
        ReviewId reviewId = new ReviewId(user, provider);
        reviewService.deleteById(reviewId);
        return "redirect:/reviews";
    }

    @PostMapping("/deleteFlag")
    public String deleteFlag(@RequestParam("username") String username, @RequestParam("providerName") String providerName) {
        User user = userService.findByUsername(username);
        Provider provider = providerService.findByName(providerName);
        ReviewId reviewId = new ReviewId(user, provider);
        Optional<Review> reviewOptional = reviewService.findById(reviewId);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setFlag(false);
            reviewService.save(review);
        }
        return "redirect:/reviews";
    }
}