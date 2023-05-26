import axios from "axios";

const api= axios.create({
  baseURL: "http://localhost:8080",
  
});

const accessToken = sessionStorage.getItem('access-token');
console.log(accessToken);
const refreshToken = sessionStorage.getItem('refresh-token');
console.log(refreshToken);


const addTokenToHeader = (config) => {
  if(accessToken)
    config.headers['access-token'] = `${accessToken}`;
  if(refreshToken)
    config.headers['refresh-token'] = `${refreshToken}`;
  
  
    return config;
};

api.interceptors.request.use(addTokenToHeader);

export default api;
