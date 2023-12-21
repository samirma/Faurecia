package com.test.faurecia.userCases

import com.test.faurecia.data.remote.Repository
import javax.inject.Inject

class getAppsListUserCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(){

    }

}