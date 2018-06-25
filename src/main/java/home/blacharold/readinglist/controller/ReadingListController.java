package home.blacharold.readinglist.controller;


import home.blacharold.readinglist.domain.Book;
import home.blacharold.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReadingListController {
    
    
    private ReadingListRepository readingListRepository;
    
    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
    
    
    @GetMapping ("/")
    public String welcome(){
        return ("readingList");
    }
    
    @GetMapping ("/{reader}")
    public String readersBooks(@PathVariable ("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }
    
    @PostMapping ("/{reader}")
    public String addToReadingList(@PathVariable ("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
