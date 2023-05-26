import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex);

const store = new Vuex.Store({
    
  state:{
    planAttraction: null,
    searchAttraction:null,
    tempoPlanAttraction:null,

    boardId:null,
    commentId:null,
    tripTeamId:null,
    tripPlanId:null,

    currentPage:1,
    itemsPerPage:5,
    totalItems:0,
    totalPages:0,
    pagedItems:[],

    isLoggedIn:false,
    isEditMode:false,

    isWriter:false,
    userId:null,

}, 
getters: {
  pagedItems(state){
    console.log(state.pagedItems)
    return state.pagedItems;
  },
  isLoggedIn(state){
      return state.isLoggedIn
    },
    getIsEditMode(state){
      return state.isEditMode
    },
    getPlanAttraction(state) {
      console.log(state.planAttraction)
      return state.planAttraction;
    },
    getTripTeamId(state){
      return state.tripTeamId;
    },
    getTripPlanId(state){
      return state.tripPlanId;
    },
    getSearchAttraction(state){
      return state.searchAttraction;
    },
    getTempoPlanAttraction(state){
      return state.tempoPlanAttraction;
    },
   
    getCurrentPage: state=>state.currentPage,
    getItemsPerPage:state=>state.itemsPerPage,
    getTotalItems:state=>state.totalItems,
    TotalPages:state=>state.totalPages,
    getIsWriter: state=>state.isWriter,
    getBoardId:state=>state.boardId,
    getCommentId:state=>state.commentId, 
    getUserId:state=>state.userId,   
  },
mutations:{
    loginMutaion(state){
        state.isLoggedIn=true;
        sessionStorage.setItem('isLoggedIn', true);
    },
    logoutMutaion(state){
        state.isLoggedIn=false;
        sessionStorage.removeItem('isLoggedIn');
    },
   
    setTripTeamId(state, tripTeamId) {
      state.tripTeamId = tripTeamId;
    },
    setTripPlanId(state, tripPlanId) {
      state.tripPlanId = tripPlanId;
    },
    setSearchAttraction(state, searchAttraction) {
      state.searchAttraction = searchAttraction;
    },
    setTotalItems(state, totalItems){
      state.totalItems=totalItems;
      state.totalPages=Math.ceil(totalItems/state.itemsPerPage);
    },
    setCurrentPage(state, currentPage){
      state.currentPage=currentPage;
    },
    setPagedItems(state, pagedItems){
      state.pagedItems=pagedItems;
    },
    setIsEditMode(state, params){
      state.isEditMode=params
    },
    setPlanAttraction(state,planAttraction){
      state.planAttraction=planAttraction
    },
    setTempoPlanAttraction(state,tempoPlanAttraction){
      state.tempoPlanAttraction=tempoPlanAttraction;
    },
    setIsWriter(state, isWriter){
      state.isWriter=isWriter;
    },
    setBoardId(state, boardId){
      state.boardId=boardId;
    },
    setCommentId(state, commentId){
      state.commentId=commentId;
    },
    setUserId(state, userId){
      state.userId=userId;
    }
},
actions:{
    login({commit}){
        return new Promise((resolve) => {
         
            setTimeout(() => {
              commit('loginMutaion');
              resolve();
            }, 1000);
          });
    },
    logout({commit}){
        console.log("asd")
        commit("logoutMutaion");
    },
    updatePlanAttraction({ commit }, planAttraction) {
      commit('setPlanAttraction', planAttraction);
    },
    updateTripTeamId({ commit }, tripTeamId) {
      commit('setTripTeamId', tripTeamId);
    },
    updateTripPlanId({ commit }, tripPlanId) {
      commit('setTripPlanId', tripPlanId);
    },
    updateSearchAttraction({ commit }, searchAttraction) {
      commit('setSearchAttraction', searchAttraction);
    },
    setPage({commit, dispatch},page){
      commit('setCurrentPage',page);
      dispatch('fetchItems');
    },
    fetchItems({commit, state},params){
      // 전체 아이템 수 저장
        commit('setTotalItems', params.length);
        // 현재 페이지에 해당하는 아이템들 계산
        const startIndex = (state.currentPage - 1) * state.itemsPerPage;
        const endIndex = startIndex + state.itemsPerPage;
        const paginatedItems = params.slice(startIndex, endIndex);
        // 현재 페이지에 해당하는 아이템들 저장
        commit('setPagedItems', paginatedItems);

    },
    updateIsEditMode({commit}, params){
      commit('setIsEditMode',params);
    },
    updateTempoPlanAttraction({commit},tempoPlanAttraction){
      commit('setTempoPlanAttraction',tempoPlanAttraction)
    },
    updateIsWriter({commit},isWriter){
      commit('setIsWriter',isWriter)
    },
    updateBoardId({commit},boardId){
      console.log(boardId)
      commit('setboardId',boardId)
    },
    updateCommentId({commit},commentId){
      commit('setCommentId',commentId)
    },
    updateUserId({commit},userId){
      commit('setUserId',userId)
    },
},
plugins: [createPersistedState()],

});

export default  store;