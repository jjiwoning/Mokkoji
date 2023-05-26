<template>
  <div>
  <b-card bg-variant="light"  class="mb-3">
    <b-form-group
      label-cols-lg="3"
      label="회원 가입"
      label-size="lg"
      label-class="font-weight-bold pt-0"
      class="mb-0"
    >
      <b-form-group
        label="아이디:"
        label-for="loginId"
        label-cols-sm="3"
        label-align-sm="right"
      >
        <b-form-input id="loginId" v-model="loginId"></b-form-input>
      </b-form-group>
      <button @click="checkIdPossible">아이디 확인</button>
      <div >
        <span v-if="idChecked">
          <span>{{ this.message }}</span>
         </span>
      </div>
      
      <b-form-group
        label="비밀번호:"
        label-for="password"
        label-cols-sm="3"
        label-align-sm="right"
      >
        <b-form-input type = "password" id="password" v-model="password"></b-form-input>
      </b-form-group>

      <b-form-group
        label="비밀번호 확인:"
        label-for="passwordConfirm"
        label-cols-sm="3"
        label-align-sm="right"
      >
        <b-form-input type = "password" id="passwordConfirm(password, passwordConfirm)" v-model="passwordConfirm"></b-form-input>
      </b-form-group>
      <button @click="checkPssword">비밀번호 일치 확인</button>
      <div >
        <span v-if="passwordChecked">
          <span>{{ this.message2 }}</span>
         </span>
      </div>
      
      <b-form-group
        label="닉네임:"
        label-for="nickname"
        label-cols-sm="3"
        label-align-sm="right"
      >
        <b-form-input id="nickname" v-model="nickname"></b-form-input>
      </b-form-group>

      <b-form-group
        label="이름 :"
        label-for="name"
        label-cols-sm="3"
        label-align-sm="right"
 
      >
      <b-form-input id="name" v-model="name"></b-form-input>
      </b-form-group>

      <b-form-group
        label="이메일 :"
        label-for="mail"
        label-cols-sm="3"
        label-align-sm="right"
    
      >
      <b-form-input id="mail" v-model="mail"></b-form-input>
      </b-form-group>

      <b-form-group
        label="전화번호 :"
        label-for="phoneNumber"
        label-cols-sm="3"
        label-align-sm="right"
     
      >
      <b-form-input id="phoneNumber" v-model="phoneNumber"></b-form-input>
      </b-form-group>


    </b-form-group>
    </b-card>
    <b-button pill variant="primary" @click="submitForm">회원 가입</b-button>
  </div>
</template>

<script>
import api from '@/util/http-common.js'

export default {
	name: 'MemberRegister',
	data() {
		return {
      message:'',
      message2:'',
      idChecked:false,
      idCheck:false,
      passwordChecked:false,
      passwordCheck:'',
			loginId: '',
			nickname: '',
			mail: '',
			passwordConfirm: '',
			name: '',
      phoneNumber:'',
      password:'',
		};
	},
  // watch: {
  //   idCheck(){
  //       if(this.idCheck===true) this.message='사용 가능한 아이디입니다. '
  //       else this.message='사용 불가능한 아이디입니다.'
  //   }
  // },
	methods: {
		submitForm() {
     
			api.post("/enjoytrip/user/signup",{
       
        loginId:this.loginId,
        password:this.password,
        name:this.name,
        nickname:this.nickname,
        mail:this.mail,
        phoneNumber:this.phoneNumber,
       
      }).then(()=>{
        alert('회원 가입이 완료되었습니다');
        this.$router.push('/')
      })
        .catch(error =>{
          alert(error)
        });
    },
    checkPssword(){
      this.passwordChecked=true
      if(this.password != this.passwordConfirm){
          this.passwordCheck=false;
      }
      else{
        this.passwordCheck=true;
      }
      this.message2 = this.passwordCheck===true? '비밀번호가 일치합니다.' : '비밀번호가 일치하지 않습니다.';

    },
    checkIdPossible(){
      api.get('/enjoytrip/user/signup/duplicate',{
        params:{loginId:this.loginId}}
      )
      .then((response)=>{
        this.idChecked=true
        console.log(response.data)
        this.idCheck  =response.data;
        this.message = this.idCheck===true? '사용 가능한 아이디입니다.' : '사용 불가능한 아이디입니다.';
        console.log(this.message)
      })
    }
  },
};
</script>