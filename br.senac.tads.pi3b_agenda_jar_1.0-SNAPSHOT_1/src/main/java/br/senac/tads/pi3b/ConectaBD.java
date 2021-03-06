/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3b;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anderson.jscosta
 */
public class ConectaBD {

    private Connection obterConexao() throws ClassNotFoundException, SQLException {

        Connection conn = null;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendabd", "root", "");

        return conn;
    }

    public void executar() {

        String querySql = "SELECT ID,NOME,DESCRICAO,PRECO_COMPRA,PRECO_VENDA,QUANTIDADE,DT_CADASTRO FROM PRODUTO";

        Lojinha loja = new Lojinha();
        
        
        
        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(querySql);
                ResultSet resultados = stmt.executeQuery()) {
            while (resultados.next()) {
       // Recebe o valor do banco 
            long id = resultados.getLong("ID");
               
                String nome = resultados.getString("NOME");
                loja.setNome(nome);            
                String descricao = resultados.getString("DESCRICAO");
                loja.setDescricao(descricao);
                double preCompra = resultados.getDouble("PRECOMPRA");
                loja.setPrecoCompra(preCompra);
               double preVenda = resultados.getDouble("PREVENDA");
                loja.setPrecoVenda(preVenda);     
                int quantidade = resultados.getInt("QUANTIDADE");
                 loja.setQuantidade(quantidade);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Lojinha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Lojinha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
