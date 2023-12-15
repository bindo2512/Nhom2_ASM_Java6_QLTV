package com.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.retails;

public interface retailDAO extends JpaRepository<retails, Integer> {
    @Modifying
    @Query("select month(r.retaildate) as retail_month,COUNT(r.retailid) AS retail_count from retails r group by month(r.retaildate)")
    public List<Object[]> countRetailsByMonth();

    @Modifying
    @Query("select count(*) as number_of_retail, r.accounts.username from retails r group by r.accounts.username")
    public List<Object[]> countRetailByUsername();

    @Modifying
    @Query("select count(r.retailid) as number_of_retail, bn.bookname from retails r join r.details d join d.books b join b.bname bn group by bn.bookname")
    public List<Object[]> countRetailByBookname();

    @Modifying
    @Query("select count(r.retailid) as number_of_retail, a.authorname from retails r join r.details d join d.books b join b.authors a group by a.authorname")
    public List<Object[]> countRetailByAuthor();

    @Modifying
    @Query("select count(r.retailid) as number_of_retail, p.publishername from retails r join r.details d join d.books b join b.publishers p group by p.publishername")
    public List<Object[]> countRetailByPublisher();

    @Modifying
    @Query("select count(r.retailid) as number_of_retail, i.issuername from retails r join r.details d join d.books b join b.issuer i group by i.issuername")
    public List<Object[]> countRetailByIssuer();

    @Modifying
    @Query("select count(r.retailid) as number_of_retail, c.categoryname from retails r join r.details d join d.books b join b.categories c group by c.categoryname")
    public List<Object[]> countRetailByCategory();


    @Modifying
    @Query(value = "select count(r.retailid) as number_of_retail, ac.username from retails r, accounts ac where MONTH(r.retaildate) = MONTH(GETDATE()) and ac.isadmin = 0 and r.username = ac.username group by ac.username", nativeQuery = true)
    public List<Object[]> countRetailByUsernameCurrentMonth();

    @Query("select r from retails r where r.verification = ?1")
    retails findByVerification(String verification);

    @Transactional
    @Modifying
    @Query("update retails r set r.isverify = true, r.verification = null where r.retailid = ?1")
    public void enable(Integer id);

    List<retails> findByIsverify(Boolean isverify);

    List<retails> findByAdminverify(Boolean adminverify);

    @Modifying
    @Query("select r from retails r join r.accounts a join r.orderstate o where DATEDIFF(day, r.returndate, ?1) >= 5 and a.username = ?2 and o.orderstateid = 2")
    public List<retails> findNearExpireRetails(Date date, String username);

    @Modifying
    @Query("select r from retails r join r.accounts a where r.isverify = false and a.username = ?1")
    public List<retails> findRentalsNeedVerify(String username);

    @Modifying
    @Query("select a.username from retails r join r.accounts a join r.orderstate o where o.orderstateid = 5")
    public List<Object[]> findAllUsernameHaveExpireRentals();

    @Transactional
    @Modifying
    @Query("update retails r set r.orderstate.orderstateid = 5 where DATEDIFF(day,r.retaildate, ?1) <= 1 and r.orderstate.orderstateid = 1")
    public void disableExpiredRentals(Date date);

    @Transactional
    @Modifying
    @Query("update retails r set r.orderstate.orderstateid = 4, r.invalidate = true where DATEDIFF(day, ?1,r.returndate) <= 1 and r.orderstate.orderstateid = 2")
    public void remindIllegalRentals(Date date);
}
