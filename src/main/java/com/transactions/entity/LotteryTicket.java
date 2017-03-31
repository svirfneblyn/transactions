package com.transactions.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lottery_tickets")

public class LotteryTicket implements Serializable {
    private static final long serialVersionUID = -1958497361120454554L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="ticket_number")
    private String number;
    @Column(name="buyer_id")
    private String buyerId;

    public LotteryTicket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LotteryTicket)) return false;

        LotteryTicket that = (LotteryTicket) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null) return false;
        return getBuyerId() != null ? getBuyerId().equals(that.getBuyerId()) : that.getBuyerId() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getBuyerId() != null ? getBuyerId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LotteryTicket{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", buyerId='" + buyerId + '\'' +
                '}';
    }
}
