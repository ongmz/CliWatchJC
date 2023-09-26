package com.example.cliwatchjc.data

import androidx.lifecycle.ViewModel
import com.example.cliwatchjc.UserManager
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val userDao: UserDao,
	private val userManager: UserManager
) : ViewModel() {

	fun createUser() {
		val newUser = User(userId = 1, userName = "Gorlock XVII")
		userDao.insertUser(user = newUser)
		userManager.setUser(newUser)
	}
}
