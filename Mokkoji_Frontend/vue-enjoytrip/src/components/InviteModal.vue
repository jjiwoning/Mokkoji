
<template>
  <div>
    <!-- 모달 버튼 -->
    <!-- <div class="text-right">
        <b-button variant="warning" @click="openModal" class="btn-block">팀원 초대</b-button>
    </div> -->
      <b-button variant="outline-primary" class="rounded-pill w-50" @click="openModal">
          <i class="bi bi-people-fill"></i> 팀원 초대
        </b-button>

    <!-- 모달 -->
    <div class="modal" :class="{ 'is-active': isModalOpen }">
      <div class="modal-background"></div>
      <div class="modal-content">
        <!-- 모달 내용 -->
        <div class="modal-header">
          <h2 class="modal-title">팀원 초대</h2>
          <button class="delete" aria-label="닫기" @click="closeModal"></button>
        </div>
        <div class="modal-body">
          <div class="field has-addons">
            <div class="control">
              <input id="search-keyword" name="nickname" v-model="nickname" type="search" class="input" placeholder="검색" aria-label="Search" />
            </div>
            <div class="control">
              <button id="btn-search" @click="searchUser" class="button is-info">검색</button>
            </div>
          </div>
          <div v-if="!users" class="notification">검색어를 입력해주세요</div>
          <div v-else>
            <b-table-simple>
              <tbody>
                <div v-for="user in users" :key="user.userId">
                  <tr>
                    <td>{{ user.nickname }}</td>
                    <td>
                      <button class="button is-primary" @click="invite(user.userId)">초대</button>
                    </td>
                  </tr>
                </div>
              </tbody>
            </b-table-simple>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/util/http-common.js'
export default {
  data() {
    return {
      isModalOpen: false, // 모달 열림/닫힘 상태 관리
      users: [],
      nickname:'',
    };
  },
  created(){
   
  },

  methods: {
    openModal() {
      this.isModalOpen = true; // 모달 열기
    },
    closeModal() {
      this.isModalOpen = false; // 모달 닫기
    },
    searchUser(){
        api.get("/enjoytrip/user/list", {params:{nickname:this.nickname}})
        .then(({data})=>{
            this.users=data
        });
    },
    invite(userId) {
  
  api.post("/enjoytrip/TripTeam/invite", {
      userId: userId,
      teamId: this.tripTeamId,
    })
    .then(() => {
      alert('초대가 완료되었습니다.');
      this.searchUser();
    })
    .catch(() => {
      alert("이미 초대된 회원입니다.")
    });
}   
  },
  computed:{
    tripTeamId(){
      return this.$store.getters.getTripTeamId;
    }
  }
};
</script>

<style>
.modal {
    
  display: none;
  top: 30%;
  left: 10%;
  width:70%;
  border-radius: 8px;
}

.modal.is-active {
  display: block;
}

.modal-background {
  
  background: white;
  position: fixed;
   padding: 30px;
}

.modal-content {
  background-color: #fff;
  margin: 10% auto;
  padding: 20px;
  border-radius: 4px;
  width: 50%;
  height: 50%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}

.close {
  color: #888;
  position: absolute;
  top: 15px;
  right: 15px;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover {
  color: #555;
}


</style>