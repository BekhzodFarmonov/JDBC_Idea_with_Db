package org.example.servise;

import org.example.dto.ContactDto;
import org.example.repository.ContactRepository;

import java.util.List;

public class ContactService {
    public  void addContact( ContactDto contactDto){
        ContactRepository contactRepository=new ContactRepository();
        ContactDto exists=contactRepository.getByPhone(contactDto.getPhone());
        boolean result=contactRepository.saveContact(contactDto);
        if(result){
            System.out.println("save contact");
        }
        else System.out.println("wrong added");
    }
    public  void contactList(){
        ContactRepository contactRepository=new ContactRepository();
        List<ContactDto> contactDtoList=contactRepository.getList();
        for (ContactDto contactDto: contactDtoList
        ) {
            System.out.println(contactDto.getId()+" "+contactDto.getName()+" "+contactDto.getSurname()+" "+contactDto.getPhone());
        }
    }
    public void deleteContact(String phone){
        ContactRepository contactRepository=new ContactRepository();
        ContactDto contactDto=contactRepository.getByPhone(phone);
        if(contactDto==null){
            System.out.println("contact nor exists");
            return;
        }
        int eff= contactRepository.delete(phone);
        if(eff==1) System.out.println("delete contact all right  ");

    }
    public void search(String query) {
        ContactRepository contactRepository=new ContactRepository();
        List<ContactDto> contactDtoList=contactRepository.search(query);
        for (ContactDto contactDto: contactDtoList
        ) {
            System.out.println(contactDto.getId()+" "+contactDto.getName()+" "+contactDto.getSurname()+" "+contactDto.getPhone());
        }

    }

}
