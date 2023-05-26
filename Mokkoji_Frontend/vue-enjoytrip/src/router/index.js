import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
//import AttractionView from "../views/AttractionView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/MemberView.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MemberLogin.vue"),
      },
      {
        path: "singin",
        name: "signIn",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MemberRegister.vue"),
      },
      {
        path: "myPage",
        name: "myPage",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MemberMyPage.vue"),
      },
      {
        path: "logout",
        name: "logout",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MemberLogout.vue"),
      },
      {
        path: "invite",
        name: "userInvite",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/InvitedList.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/BoardView.vue"),
    //redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:boardId",
        name: "boardDetail",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardDetail.vue" ),
         
      },
      // {
      //   path: "detail/:boardId",
      //   name: "boardDetail",
      //   component: () =>
      //     import(/* webpackChunkName: "board" */  "@/components/comment/CommentList"),
         
      // },
     
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:boardId",
        name: "boardDelete",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/enjoytrip",
    name: "enjoytrip",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AttractionView.vue"),
    children: [
      {
        path: "attraction",
        name: "attraction",
        components:()=>import(/* webpackChunkName: "board" */ "@/components/attraction/AttractionSearchMenuVue.vue"),
      },
      {
        path: "attraction",
        name: "attraction",
        components:()=>import(/* webpackChunkName: "board" */ "@/components/attraction/AttractionListVue.vue"),
      },
    ],
  },
  {
    path: "/tripPlan",
    name: "tripPlan",
    redirect:"/tripPlan/list",
    component: () => import(/* webpackChunkName: "board" */ "@/views/TripPlanView.vue"),
    children: [
      {
        path: "list",
        name: "tripTeamList",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/TripTeamList.vue"),
      },

      {
        path: "addTripTeam",
        name: "addTripTeam",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/addTripTeam.vue"),
      },
      {
        path: "modifyTripTeam",
        name: "modifyTripTeam",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/TripTeamModify.vue"),
      },
      {
        path: "addPlan",
        name: "addPlan",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/plan/addTripPlan.vue"),
      },
      // {
      //   path: ":no",
      //   name: "tripPlanDetail",
      //   component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/TripPlanDetailVue.vue"),
      // },
      {
        path: "TripTeamdetail",
        name: "TripTeamdetail",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/TripTeamDetail.vue"),
      },
      {
        path: "addPlanAttraction",
        name: "addPlanAttraction",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/plan/AddTripPlanDetail.vue"),
      },
      {
        path: "tripPlanDetail",
        name: "tripPlanDetail",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/plan/TripPlanDetail.vue"),
      },
      {
        path: "tripPlanModify",
        name: "tripPlanModify",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/plan/TripPlanModify.vue"),
      },
      {
        path: "teamBoard",
        name: "teamBoard",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/board/TeamBoard.vue"),
      },
      {
        path: "teamBoardRegister",
        name: "teamBoardRegister",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/board/TeamBoardRegister.vue"),
      },
      {
        path: "teamBoardDetail",
        name: "teamBoardDetail",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/board/TeamBoardDetail.vue"),
      },
      {
        path: "teamBoardModify",
        name: "teamBoardModify",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/board/TeamBoardModify.vue"),
      },
      {
        path: "teamBoardDelete",
        name: "teamBoardDelete",
        component: () => import(/* webpackChunkName: "board" */ "@/components/tripPlan/team/board/TeamBoardDelete.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
