package com.company.ht11.library.main;

import com.company.ht11.library.entity.*;
import com.company.ht11.library.service.FileService;
import com.company.ht11.library.service.find.FindByAuthor;
import com.company.ht11.library.service.find.Findable;
import com.company.ht11.library.service.sort.SortedByPublishingYear;
import com.company.ht11.library.service.sort.SortedByTitle;
import com.company.ht11.library.view.PrintAsTable;
import com.company.ht11.library.view.Printable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Library shell = new Library();

        String fileName = System.getProperty("user.dir") + "\\src\\com\\company\\ht11\\library\\file\\inputData.txt";
        ArrayList<String> fileContext = new ArrayList<>();
        FileService.readFile(fileName, fileContext);
        FileService.addToLibrary(shell, fileContext);

        Printable printer = new PrintAsTable();
        printer.print(shell.getUnits());

        Findable matcher = new FindByAuthor("Author1");
        List<PrintedEditions> lists = shell.find(matcher);
        System.out.print("\nFind Results:");
        printer.print(lists);

        System.out.print("\nSort By Publishing Year");
        Comparator<PrintedEditions> comparator = new SortedByPublishingYear();
        PrintedEditions[] array = new PrintedEditions[lists.size()];
        array = lists.toArray(array);
        Arrays.sort(array, comparator);
        printer.print(Arrays.asList(array));

        System.out.print("\nSort By Title");
        comparator = new SortedByTitle();
        Arrays.sort(array, comparator);
        printer.print(Arrays.asList(array));
    }
}
