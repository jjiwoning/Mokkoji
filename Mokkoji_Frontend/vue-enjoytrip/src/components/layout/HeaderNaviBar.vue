<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/img/moggogi.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'attraction' }" class="link" style="color: black;">
      <b-icon icon="camera" font-scale="1.5"></b-icon> 지역별 여행지
    </router-link>
      </b-nav-item>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'boardList' }" class="link" style="color: black;"
              ><b-icon icon="share" font-scale="1.5"></b-icon> Q&A 게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'tripPlan' }" class="link" style="color: black;"
              ><b-icon icon="journal-bookmark" font-scale="1.5"></b-icon> 여행 계획</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

    <b-navbar>
  <b-navbar-nav class="ml-auto justify-content-end"> <!-- 오른쪽 정렬 -->
    <!-- <b-nav-form>
      <b-form-input class="mr-sm-2" placeholder="Search"></b-form-input>
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form> -->
  
    <b-nav-item-dropdown right>
      <template #button-content>
        <b-icon icon="person" font-scale="2"></b-icon>
      </template>
       <div v-if="isLoggedIn === false">
        <b-dropdown-item href="#">
            <router-link :to="{ name: 'signUp' }" class="link">
              <b-icon icon="person-plus"></b-icon> 회원가입
            </router-link>
          </b-dropdown-item>
           <b-dropdown-item href="#">
            <router-link :to="{ name: 'signIn' }" class="link">
              <b-icon icon="lock"></b-icon> 로그인
            </router-link>
          </b-dropdown-item>
       </div>
      <div v-else>
      <b-dropdown-item href="#">
        <router-link :to="{ name: 'myPage' }" class="link">
          <b-icon icon="person-square"></b-icon> 마이페이지
        </router-link>
      </b-dropdown-item>
      <b-dropdown-item href="#">
        <router-link :to="{ name: 'logout' }" class="link">
          <b-icon icon="unlock"></b-icon> 로그아웃
        </router-link>
      </b-dropdown-item>
      </div>
    </b-nav-item-dropdown>
  </b-navbar-nav>
</b-navbar>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
// import { mapState } from 'vuex';

import { mapGetters } from 'vuex'
export default {
  name: "HeaderNaviBar",
  data(){
    return{
    }
  },
  computed:{
    // ...mapState('store',['isLoggedIn']),
    ...mapGetters(["isLoggedIn"]),
    // isLoggedIn() {
      // console.log(sessionStorage.getItem('isLoggedIn'))
      // return sessionStorage.getItem('isLoggedIn');
    // }
  }
};
</script>

<style></style>
