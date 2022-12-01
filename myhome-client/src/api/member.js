import _axios from "@/plugins/axios";

async function login(user, success, fail) {
  console.log(user);
  await _axios.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}
async function regist(user, success, fail) {
  await _axios.post(`/user/regist`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  _axios.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await _axios.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  _axios.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await _axios.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await _axios.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function userUpdate(user, success, fail) {
  await _axios.put(`/user`, user).then(success).catch(fail);
}

async function userDelete(userid, success, fail) {
  await _axios.delete(`/user/${userid}`).then(success).catch(fail);
}




export { login, regist, findById, tokenRegeneration, logout , userUpdate, userDelete};
