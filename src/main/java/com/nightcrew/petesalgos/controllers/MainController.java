package com.nightcrew.petesalgos.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nightcrew.petesalgos.models.Problem;
import com.nightcrew.petesalgos.models.User;
import com.nightcrew.petesalgos.services.ProblemService;
import com.nightcrew.petesalgos.services.UserService;

@Controller
public class MainController {
  @Autowired
  private ProblemService problemService;

  @Autowired
  private UserService userService;

// ============== Display Algo By Id ==================
  @GetMapping("/algo/{id}")
  public String algo(@PathVariable("id") Long id, Model model, HttpSession session) {
    Boolean notInSession = session.getAttribute("userId") == null;
    if(notInSession){
      return "redirect:/";
    } 
    else{
      Problem problem = problemService.getProblem(id);
      model.addAttribute("problem", problem);
// ================== Get Solved Problems By User Id ============
    Long userId = (Long) session.getAttribute("userId");
    User loggedInUser = userService.getOneUser(userId);
    List<Problem> solvedProblems = loggedInUser.getProblemsSolved();
    Set<Long> solvedProblemIds = new HashSet<>();
    for(Problem algo : solvedProblems){
      solvedProblemIds.add(algo.getId());
    }
    model.addAttribute("solvedProblemIds", solvedProblemIds);
    return "algo.jsp";
    }
  }

  // ============= Add Solved Algo By userId and problemId ===================
  @PostMapping("/solved/{userId}/{problemId}")
  public String addSolvedProblem(@PathVariable("userId") Long userId, 
      @PathVariable("problemId") Long problemId, HttpSession session){
    Boolean notInSession = session.getAttribute("userId") == null;
    if(notInSession){
      return "redirect:/";
    }
    if(!session.getAttribute("userId").equals(userId)){
      return "algo.jsp";
    }
    userService.addSolvedProblem(userId, problemId);
    return "redirect:/dashboard";
  }

// ============= Delete Solved Algo By userId and problemId ===================
  @DeleteMapping("/delete/{userId}/{problemId}")
  public String deleteSolvedProblem(@PathVariable("userId") Long userId, 
      @PathVariable("problemId") Long problemId){
    userService.deleteSolvedProblem(userId, problemId);
    return "redirect:/dashboard";
  }

}
