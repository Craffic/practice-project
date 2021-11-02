package com.craffic.practice.action;

import com.craffic.practice.domain.Auth;
import com.craffic.practice.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class BookController {

    /**
     * 注入redis模板
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/book_list")
    public ModelAndView books() {
        List<Book> books = new ArrayList();
        Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("罗贯中");
        b1.setName("三国演义");
        Book b2 = new Book();
        b2.setId(2);
        b2.setAuthor("曹雪芹");
        b2.setName("红楼梦");
        books.add(b1);
        books.add(b2);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    /**
     * 获取ControllerAdvice全局数据
     */
    @GetMapping("query/global_data")
    @ResponseBody
    public void getGlobalData(Model model){
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println("全局数据：key=" + key + " value=" + value);
        }
    }

    /**
     * 请求参数预处理
     * Book和Auth这两个实体类都有name相同属性
     * 在controller上同时接收这两个实体的数据
     * 在传递参数中，两个相同的name会造成混淆，@ControllerAdvice和@InitBinder解决问题
     * http://localhost:8080/practice/book_info?book.name=sanguoyanyi&book.auth=luo&auth.name=luoguanzhong&auth.age=33
     */
    @GetMapping("book_info")
    @ResponseBody
    public String bookInfo(@ModelAttribute("book") Book book, @ModelAttribute("auth")Auth auth){
        return book.toString() + "-----------" + auth.toString();
    }

    /**
     * 整合redis案例
     */
    @GetMapping("redis_book")
    @ResponseBody
    public String test1(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "三国演义");
        String bookName = ops.get("name");
        System.out.println(bookName);

        ValueOperations ops1 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setId(1);
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        ops1.set("book", book);
        Book book1 = (Book)ops1.get("book");
        System.out.println(book1.toString());
        return "success";
    }
}
