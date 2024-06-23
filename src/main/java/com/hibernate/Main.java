package com.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.*;

public class Main {

    private static <T>List<T> loadAllData(Class<T> type, Session session){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        criteriaQuery.from(type);
        List<T> data = session.createQuery(criteriaQuery).getResultList();
        return data;
    }

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Choice:");
        int option = scanner.nextInt();
    switch (option) {
        case 1:
            System.out.println("Enter the pokemon id no.");
            int id = scanner.nextInt();
            System.out.println("Enter the pokemon name:");
            String pokemonName = scanner.next();
            Pokemon pokemon3 = new Pokemon();
            pokemon3.setId(id);
            pokemon3.setName(pokemonName);
            session.save(pokemon3);
            transaction.commit();
            System.out.println("Pokemon is created");
            System.out.println("Pokemon Id is: "+ pokemon3.getId());
            System.out.println("Pokemon Name is: "+pokemon3.getName());
            break;

        case 2:
            System.out.println("Enter the pokemon id no.");
            Integer getId = scanner.nextInt();
            Pokemon pokemon1 = session.get(Pokemon.class, getId);
            System.out.println("Pokemon is called");
            System.out.println("Pokemon id is: " + pokemon1.getId());
            System.out.println("Pokemon name is: " + pokemon1.getName());
            transaction.commit();
            break;

        case 3:
            System.out.println("Enter the pokemon id no.");
            Integer loadId = scanner.nextInt();
            Pokemon pokemon2 = session.load(Pokemon.class, loadId);
            transaction.commit();
            System.out.println("Pokemon is loaded");
            System.out.println("Pokemon id is: " + pokemon2.getId());
            System.out.println("Pokemon name is: " + pokemon2.getName());
            break;

        case 4:
            System.out.println("Enter the pokemon id no.");
            int updateId = scanner.nextInt();
            Pokemon pokemon4 = session.get(Pokemon.class, updateId);
            System.out.println("Pokemon is called");
            System.out.println("Pokemon id is: " + pokemon4.getId());
            System.out.println("Pokemon name is: " + pokemon4.getName());
            System.out.println("Enter the pokemon name:");
            String pokemonName1 = scanner.next();
            pokemon4.setName(pokemonName1);
            session.saveOrUpdate(pokemon4);
            System.out.println("Pokemon is updated");
            System.out.println("Pokemon id is: " + pokemon4.getId());
            System.out.println("Pokemon name is: " + pokemon4.getName());
            transaction.commit();
            break;

        case 5:
            System.out.println("Enter the pokemon id no.");
            int deleteId = scanner.nextInt();
            Pokemon pokemon5 = session.get(Pokemon.class, deleteId);
            session.delete(pokemon5);
            transaction.commit();
            System.out.println("Pokemon is deleted");
            System.out.println("Pokemon id is: " + pokemon5.getId());
            System.out.println("Pokemon name is: " + pokemon5.getName());
            break;

        case 6:
            List<Pokemon> pokemonList = loadAllData(Pokemon.class, session);
            System.out.println("Get All Pokemons");
            System.out.println(pokemonList);
            transaction.commit();
            break;

        default:
            if (option > 6) {
                System.out.println("Enter options between 1 to 6");
                break;
            }
    }

session.close();
        sessionFactory.close();
    }
}