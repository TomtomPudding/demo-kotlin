package com.chartapp.login.config


import com.chartapp.login.handler.SuccessAccessHandler
import com.chartapp.login.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



/**
 * SpringSecurityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
 * @author aoi
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    @Autowired
    private val userDetailsService: UserDetailsServiceImpl? = null

    //フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * 認可設定を無視するリクエストを設定
     * 静的リソース(image,javascript,css)を認可処理の対象から除外する
     */
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**"
        )
    }

    /**
     * 認証・認可の情報を設定する
     * 画面遷移のURL・パラメータを取得するname属性の値を設定
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/login") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
                    .loginPage("/login") //ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
                    .usernameParameter("name") //リクエストパラメータのname属性を明示
                    .passwordParameter("password")
                    .successHandler(SuccessAccessHandler())
                    .failureUrl("/login?error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
    }


    /**
     * 認証時に利用するデータソースを定義する設定メソッド
     * ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている
     * @param auth
     * @throws Exception
     */
    @Autowired
    @Throws(Exception::class)
    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

}