package com.tom.demokotlin.login.dao

import com.tom.demokotlin.login.entity.LoginUserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

/**
 * DBへのアクセスメソッドを呼び出すDao
 * @author aoi
 */
@Repository
class LoginUserDao {

    @Autowired
    internal var em: EntityManager? = null

    /**
     * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
     * @param name
     * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
     */
    fun findUser(name: String): LoginUserEntity {
        var query = ""
        query += "SELECT * "
        query += "FROM users "
        query += "WHERE name = :name " //setParameterで引数の値を代入できるようにNamedParameterを利用

        //EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
        return em!!.createNativeQuery(query, LoginUserEntity::class.java).setParameter("name", name)
                .getSingleResult() as LoginUserEntity
    }

}