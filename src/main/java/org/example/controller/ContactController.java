package org.example.controller;

import org.example.db.DatabaseUtil;
import org.example.dto.ContactDto;
import org.example.servise.ContactService;

import java.util.Scanner;

public class ContactController {
    static ContactService contactService=new ContactService();
    static Scanner scanString=new Scanner(System.in);
    static Scanner scanInt=new Scanner(System.in);
    public  void start() {
        DatabaseUtil.createTable();
        boolean bool=true;
        while (bool){
            showMenu();
            int action=action();
            switch (action){
                case 1:
                    System.out.println("Add Contact");
                    addContact();break;
                case 2:
                    System.out.println("Contact List");
                    contactList();break;
                case 3:
                    System.out.println("Delate Contact");
                    deleteContact();break;
                case 4:
                    System.out.println("Search Contact");
                    search();break;
                default:bool=false;

            }
        }
    }

    public  void showMenu() {
        System.out.println("**MENU**");
        System.out.println("1.Add Contact");
        System.out.println("2.Contact List");
        System.out.println("3.Delate Contact");
        System.out.println("4.Search Contact");
        System.out.println("0.Exit");
    }
    public int action(){
        System.out.println("Enter Action:");
        return scanInt.nextInt();
    }
    public   void addContact(){
        System.out.println("enter name");
        String name=scanString.nextLine();
        System.out.println("enter surname");
        String surname=scanString.nextLine();
        System.out.println("enter phone");
        String phone=scanString.nextLine();
        ContactDto contactDto=new ContactDto(name,surname,phone);
        contactService.addContact(contactDto);
    }
    public  void contactList(){
        contactService.contactList();
    }
    public void deleteContact(){
        System.out.println("enter phone");
        String phone=scanString.nextLine();
        contactService.deleteContact(phone);
    }
    private  void search() {
        System.out.println("enter query");
        String query=scanString.nextLine();
        contactService.search(query);

    }

}
