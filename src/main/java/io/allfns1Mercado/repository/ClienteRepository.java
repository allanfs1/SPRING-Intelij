package io.allfns1Mercado.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

    ClienteRepository(){
        System.out.println("Conexaoc de ligação");
    }



    public void persisitirFornecedor(){
        System.out.println("Persistir fornecedor");
    }

    public void InsertFornecedor(){
        System.out.println("Persistir Insert");
    }

    public void Remove(){
        System.out.println("Persistir Remove");
    }


}
