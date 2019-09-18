package com.app.demo.Model;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table (name="currency")
public class Currency{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String symbol;

    public Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Currency(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    /*
    public boolean equals(Object o) {
        if(o instanceof Currency ) {
            return ((Currency) o).getId() == this.getId();
        }
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return symbol.equals(currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
