package com.example.cliwatchjc.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.UserManager
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val userDao: UserDao,
	private val userManager: UserManager
) : ViewModel() {

	private val _feedback = MutableStateFlow<String?>(null)
	val feedback: StateFlow<String?> = _feedback.asStateFlow()

	private val _isAuthenticated = MutableStateFlow<Boolean>(false)
	val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated.asStateFlow()

	init {
		// Initial check to set the authentication state when the ViewModel is created
		_isAuthenticated.value = (userManager.currentUser.value != null)
	}

	fun registerUser(userName: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
		val existingUser = userDao.getUser(userName, password)
		if (existingUser == null) {
			val newUser = User(userName = userName, password = password)
			val userId = userDao.insertUser(newUser)
			val updatedUser = newUser.copy(userId = userId.toInt())
			userManager.setUser(updatedUser)
			_feedback.value = "Registration successful!"
			_isAuthenticated.value = true
		} else {
			_feedback.value = "User already exists!"
		}
	}

	fun loginUser(userName: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
		val user = userDao.getUser(userName, password)
		if (user != null) {
			userManager.setUser(user)
			_feedback.value = "Login successful!"
			_isAuthenticated.value = true
		} else {
			_feedback.value = "Invalid username or password!"
		}
	}
}


