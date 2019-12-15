package com.chartapp.login.service

import com.chartapp.jooq.codes.foobar.cv.tables.records.UsersRecord
import com.chartapp.login.dao.LoginUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Spring Securityのユーザ検索用のサービスの実装クラス
 * DataSourceの引数として指定することで認証にDBを利用できるようになる
 * @author tom
 *
 */
@Service
class UserDetailsServiceImpl: UserDetailsService{

	//DBからユーザ情報を検索するメソッドを実装したクラス
	@Autowired
	lateinit var userDao: LoginUserDao

	/**
	 * UserDetailsServiceインタフェースの実装メソッド
	 * フォームから取得したユーザ名でDBを検索し、合致するものが存在したとき、
	 * パスワード、権限情報と共にUserDetailsオブジェクトを生成
	 * コンフィグクラスで上入力値とDBから取得したパスワードと比較し、ログイン判定を行う
	 */
	override fun loadUserByUsername(userName:String): UserDetails{

		var user :UsersRecord = userDao.findUser(userName)

		if (user.userName.equals("")) {
			throw UsernameNotFoundException("User" + userName + "was not found in the database");
		}
		//権限のリスト
		//AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
		//権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
		var grantList: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()
		var authority :GrantedAuthority = SimpleGrantedAuthority("USER");
		grantList.add(authority);

		//rawDataのパスワードは渡すことができないので、暗号化
		var  encoder:BCryptPasswordEncoder = BCryptPasswordEncoder();

		//UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトを
		var userDetails: UserDetails = User(user.userName, encoder.encode(user.password),grantList)

		return userDetails;
	}

}