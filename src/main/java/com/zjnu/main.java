package com.zjnu;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/12/19.
 */
public class main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        // TODO Auto-generated method stub
        PersonService personService=(PersonService) Naming.lookup("rmi://10.7.90.243:6600/PersonService");
                PersonProxy proxy = new PersonProxy();
        PersonService bookfacade = (PersonService) proxy.bind(personService);
        List<PersonEntity> personList=bookfacade.GetList();
        for(PersonEntity person:personList){
            System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());
        }
    }

}
