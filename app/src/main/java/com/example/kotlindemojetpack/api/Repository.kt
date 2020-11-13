package com.example.kotlindemojetpack.api

import com.example.kotlindemojetpack.reponse.DiscoveryBean

/**
 *  create by pan yi on 2020/11/6
 *  desc : 网络仓库
 */
class Repository {
    companion object {
        suspend fun getDiscovery(): DiscoveryBean {
            return NetConfig.apiService.getDiscovery()
        }

        suspend fun getHotSearch(): List<String> {
            return NetConfig.apiService.getHotSearch()
        }
    }
}