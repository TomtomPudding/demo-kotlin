package com.chartapp.login.dao

import com.chartapp.jooq.codes.foobar.cv.tables.Users
import com.chartapp.jooq.codes.foobar.cv.tables.records.UsersRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * DBへのアクセスメソッドを呼び出すDao
 * @author tom
 */
@Repository
@Transactional
class LoginUserDao(private val dsl: DSLContext) {


    /**
     * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
     * @param name
     * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
     */
    fun findUser(name: String): UsersRecord
        = dsl.select()
            .from(USERS)
            .where(USERS.USER_NAME.eq(name))
            .fetchOneInto(UsersRecord::class.java)


    companion object {
        private val USERS = Users.USERS
    }
}