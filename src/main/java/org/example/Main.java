package org.example;

import org.example.controller.ContactController;
import org.example.db.DatabaseUtil;
import org.example.dto.ContactDto;
import org.example.servise.ContactService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactController controller = new ContactController();
        controller.start();
    }
}