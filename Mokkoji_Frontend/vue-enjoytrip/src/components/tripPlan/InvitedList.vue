<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3>초대 목록</h3>
    <b-row>
      <b-col v-if="invitations.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th>초대 번호</b-th>
              <b-th>팀 아이디</b-th>
              <b-th>팀 이름</b-th>
              <b-th>초대</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr v-for="invitation in invitations" :key="invitation.userTripTeamId">
              <b-td>{{invitation.userTripTeamId}}</b-td>
              <b-td>{{invitation.tripTeamId}}</b-td>
              <b-td>{{invitation.teamName}}</b-td>
              <b-td>
                 <b-button variant="success" @click="acceptInvitation(invitation.tripTeamId, invitation.userTripTeamId)">초대 수락</b-button>
                <b-button variant="danger" @click="refusetInvitation(invitation.tripTeamId, invitation.userTripTeamId)">초대 거절</b-button>
              </b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-col>
      <b-col v-else class="text-center">초대 목록이 없습니다.</b-col>
    </b-row>
  </b-container>
</template>


<script>
 import api from '@/util/http-common.js'
  export default {
    
    name: "InvitedList",
    data(){
        return {
            invitations:[],
        }
    },
    created(){
        this.loadInvitations();
    },
    methods:{
         acceptInvitation(tripTeamId, userTripTeamId) {
            api.post(`/enjoytrip/TripTeam/${tripTeamId}/invite/${userTripTeamId}/accept`)
            .then(()=>{
                alert('초대를 수락하셨습니다. ')
               this.loadInvitations();
            })
        },
        loadInvitations(){
            api.get("/enjoytrip/user/invite")
            .then(({ data }) => {
            this.invitations=data;
            });
        },
        refusetInvitation(tripTeamId, userTripTeamId){
           api.post(`/enjoytrip/TripTeam/${tripTeamId}/invite/${userTripTeamId}/refuse`)
            .then(()=>{
                alert('초대를 거절하셨습니다. ')
               this.loadInvitations();
            })
        }
    }
  }
  </script>
  <style scoped>
  .underline-green {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(27, 231, 75, 0.3) 30%);
  }
  </style>
