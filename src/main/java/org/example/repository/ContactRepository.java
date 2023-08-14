package org.example.repository;

import org.example.dto.ContactDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {
    public boolean saveContact(ContactDto contactDto){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
            String sql="insert into contact(name,surname,phone) values(?,?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, contactDto.getName());
            preparedStatement.setString(2, contactDto.getSurname());
            preparedStatement.setString(3, contactDto.getPhone());
            preparedStatement.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public ContactDto getByPhone(String phone){
        ContactDto contactDto=null;
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
            String sql="select id,name,surname,phone from contact where phone=?";
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                contactDto = new ContactDto();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone1 = resultSet.getString("phone");
                contactDto.setId(id);
                contactDto.setName(name);
                contactDto.setSurname(surname);
                contactDto.setPhone(phone1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDto;
    }
     public List<ContactDto> getList(){
         Connection con=null;
        List<ContactDto> contactDtoList=new LinkedList<>();
         try {
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
             Statement statement=con.createStatement();
             ResultSet resultSet=statement.executeQuery("Select * FROM contact");
             while (resultSet.next()){
             Integer id=resultSet.getInt("id");
             String name=resultSet.getString("name");
             String surname=resultSet.getString("surname");
             String phone=resultSet.getString("phone");
             ContactDto contactDto=new ContactDto(name,surname,phone,id);
             contactDtoList.add(contactDto);}

             return contactDtoList;

         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             if (con!=null) {
                 try {
                     con.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

         }
return contactDtoList;
     }
     public  int delete(String phone) {
        Connection con=null;
         try {
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
             PreparedStatement preparedStatement= con.prepareStatement("delete  from contact where phone=?");
             preparedStatement.setString(1,phone);
             int effectedRows=preparedStatement.executeUpdate();
             return effectedRows;
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             if (con!=null) {
                 try {
                     con.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

         }
         return 0;

     }
     public List<ContactDto> search( String query){
        List<ContactDto> contactDtoList=new LinkedList<>();
         Connection con=null;
         try {
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
             String sql="SELECT * from contact where lower(name) like ? or  lower(surname) like ? or  lower(phone) like ?";
             String param="%"+query.toLowerCase()+"%";
             PreparedStatement preparedStatement= con.prepareStatement(sql);
             preparedStatement.setString(1,param);
             preparedStatement.setString(2,param);
             preparedStatement.setString(3,param);
             ResultSet resultSet=preparedStatement.executeQuery();
             if(resultSet.next()) {
                 ContactDto contactDto = new ContactDto();
                 contactDto.setId(resultSet.getInt("id"));
                 contactDto.setName(resultSet.getString("name"));
                 contactDto.setSurname(resultSet.getString("surname"));
                 contactDto.setPhone(resultSet.getString("phone"));
                 contactDtoList.add(contactDto);
             }


         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             if (con!=null) {
                 try {
                     con.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

         }

return contactDtoList;
     }
}
