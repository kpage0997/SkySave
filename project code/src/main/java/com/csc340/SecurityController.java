package com.csc340;

import com.csc340.provider.Provider;
import com.csc340.provider.ProviderService;
import com.csc340.review.Review;
import com.csc340.review.ReviewId;
import com.csc340.user.User;
import com.csc340.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.csc340.review.ReviewService;
import com.csc340.user.UserRepository;

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

    @GetMapping("/")
    public String index() {
        return "HomePage";
    }

    @GetMapping("/HomePage")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/home")
    public String home() {
        return "HomePage";
    }

    @GetMapping("/serviceproviderlist")
    public String serviceproviderlist() {
        return "serviceproviderlist";
    }

    @GetMapping("/reviews")
    public String reviews(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        List<Review> reviews = reviewService.findAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @GetMapping("/userlist")
    public String adminusers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactus";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @GetMapping("/admin")
    public String adminHello(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "admin";
    }

    @GetMapping("/mod")
    public String modHello(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "mod";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "hello";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
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


    @PostMapping("/create")
    public void createUser(User user) {

        userService.saveUser(user);
    }

    @PostMapping("/username-exists")
    public String usernameExists() {

        return "username-exists";
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