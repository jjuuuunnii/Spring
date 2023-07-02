package hello.item.service.pratice.controller;

import hello.item.service.pratice.domain.Item;
import hello.item.service.pratice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("addStatus",true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/edit/{itemId}")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/edit/{itemId}")
    public String editItem(@PathVariable Long itemId,
                           @ModelAttribute Item item,
                           RedirectAttributes redirectAttributes) {

        itemRepository.update(itemId, item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("editStatus", true);

       return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/delete")
    public String deleteForm(){
        return "/basic/deleteForm";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam Long itemId, Model model, RedirectAttributes redirectAttributes) {
        itemRepository.deleteItem(itemId);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("deleteStatus", true);
        return "redirect:/basic/items";
    }


}
