package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String save(@RequestParam String itemName,
//                       @RequestParam Integer price,
//                       @RequestParam Integer quantity,
//                       Model model){
//        Item item = new Item(itemName,price,quantity);
//
//        itemRepository.save(item);
//        model.addAttribute("item", item);
//        return "/basic/item";
//    }
//
//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item){
//        itemRepository.save(item);
//        //model.Attribute("item",item); //자동추가, 생략가능
//
//        return "/basic/item";
//    }

//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item){
//        itemRepository.save(item);
//        //model.Attribute("item",item); //자동추가, 생략가능=
//        return "redirect:/basic/items/"+item.getId();
//    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        //model.Attribute("item",item); //자동추가, 생략가능
        return "redirect:/basic/items/{itemId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

//    @PostMapping("/{itemId}/edit")
//    public String editItem(@PathVariable Long itemId,
//                       @RequestParam String itemName,
//                       @RequestParam Integer price,
//                       @RequestParam Integer quantity,
//                       Model model){
//        Item updateParam = new Item(itemName, price, quantity);
//        itemRepository.update(itemId,updateParam);
//        Item item = itemRepository.findById(itemId);
//        model.addAttribute(item);
//        return "/basic/item";
//    }

    @PostMapping("/{itemId}/edit")
    public String editItemV2(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }





//    @PostMapping("/add")
//    public String addItem(){
//
//    }



}
