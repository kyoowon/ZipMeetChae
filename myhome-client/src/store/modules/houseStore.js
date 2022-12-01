import _axios from "@/plugins/axios";


function binaryAptCodeSearch(target, dataArray) {
    let low = 0;
    let high = dataArray.length - 1;

    while (low <= high) {
        let mid = Math.floor((high + low) / 2);
        let guess = dataArray[mid].aptCode;

        if (guess === target) {
            return guess;
        } else if (guess > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return -1;
}


const houseStore = {
    namespaced: true,
    state: {
        sidos: [{
            value: null,
            text: "선택하세요",
        }, ],
        guguns: [{
            value: null,
            text: "선택하세요",
        }, ],
        dongs: [{
            value: null,
            text: "선택하세요",
        }, ],
        houses: [],
        deals: [],
        statssido: [],
        statsgugun: [],
        statsdong: [],
        favorite: [],
        isUpdateFav: false,
        house: null,
        error: false,
    },
    getters: {
        getChartDataformat(state) {
            let chart = {
                title: "매매 변동",
                datas: [],
                labels: [],
            };
            state.deals.forEach((deal) => {
                chart.datas.push(Number(deal.dealAmount.replace(/[^0-9.-]+/g, "")));
                chart.labels.push(`${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}`);
            });
            return chart;
        },
        getFieldsAptInfo(state) {
            let fields = []
            let years = new Date().getFullYear();
            state.houses.forEach((house) => {
                let cal = Math.abs(parseInt(years) - parseInt(house.buildYear));
                let type = "신축";
                if (cal >= 5) {
                    type = "5년 이상";
                } else if (cal >= 10) {
                    type = "10년 이상";
                }
                fields.push({
                    "apartmentName": house.apartmentName,
                    "buildtype": type,
                    "aptCode": house.aptCode,
                    "fovarite": binaryAptCodeSearch(house.aptCode, state.favorite) > 0 ? true : false,
                    "lat": house.lat,
                    "lng": house.lng,
                })
            })
            console.log(fields);
            return fields;
        },
        getFieldFavorite(state) {
            let fields = [];
            let years = new Date().getFullYear();
            state.favorite.forEach((fav) => {
                let cal = Math.abs(parseInt(years) - parseInt(fav.buildYear));
                let type = "신축";
                if (cal >= 5) {
                    type = "5년 이상";
                } else if (cal >= 10) {
                    type = "10년 이상";
                }
                fields.push({
                    "apartmentName": fav.apartmentName,
                    "buildtype": type,
                    "aptCode": fav.aptCode,
                    "fovarite": true,
                    "lat": fav.lat,
                    "lng": fav.lng,
                })
            })
            console.log(fields);
            return fields;
        },
        getStateChart(state) {
            let chart = {
                title: "test",
                datas: [],
                labels: [],
            };
            if (state.sidos.value == null) {
                chart.title = "시도별 매매금액 차트";
                state.statssido.forEach((sido) => {
                    chart.labels.push(sido.sidoName);
                    chart.datas.push(sido.avgDeal);
                })
            } else if (state.sidos.value != null) {
                let chart = {
                    title: null,
                    labels: [],
                    datas: [],
                };
                chart.title = "구군별 매매금액 차트";
            }
            return chart;
        },
        getdetailChart(state) {
            let chart = {
                title: null,
                datas: [],
                labels: [],
            };
            if (deals != null) {
                chart.title = "년도별 매매금액 차트";
                state.deals.forEach((deal) => {
                    chart.labels.push(sido.sidoName);
                    chart.datas.push(sido.avgDeal);
                })
            }
            return chart;
        }
    },
    mutations: {
        /////////////////////////////// House start /////////////////////////////////////
        SET_SIDO_LIST(state, sidos) {
            sidos.forEach((sido) => {
                state.sidos.push({
                    value: sido.sidoCode,
                    text: sido.sidoName,
                });
            });
        },
        SET_GUGUN_LIST(state, guguns) {
            guguns.forEach((gugun) => {
                state.guguns.push({
                    value: gugun.gugunCode,
                    text: gugun.gugunName,
                });
            });
        },
        SET_DONG_LIST(state, dongs) {
            dongs.forEach((dong) => {
                state.dongs.push({
                    value: dong.dongcode,
                    text: dong.dongName,
                });
            });
        },
        CLEAR_SIDO_LIST(state) {
            state.sidos = [{
                value: null,
                text: "선택하세요",
            }, ];
        },
        CLEAR_GUGUN_LIST(state) {
            state.guguns = [{
                value: null,
                text: "선택하세요",
            }, ];
        },
        CLEAR_DONG_LIST(state) {
            state.dongs = [{
                value: null,
                text: "선택하세요",
            }, ];
        },
        CLEAR_APT_LIST(state) {
            state.houses = [];
            state.house = null;
        },
        SET_HOUSE_LIST(state, houses) {
            state.houses = houses;
        },
        SET_DEAL_LIST(state, deals) {
            state.deals = deals;
        },
        SET_STATS_SIDO_LIST(state, statssido) {
            state.statssido = statssido.data;
        },
        SET_DETAIL_HOUSE(state, house) {
            // console.log("Mutations", house);
            state.house = house;
        },
        SET_FAVORITE(state, status) {
            if (status.status != 200) {
                state.error = true;
            }
            state.favorite = status.data;
            state.error = false;
        }
        /////////////////////////////// House end /////////////////////////////////////
    },
    actions: {
        /////////////////////////////// House start /////////////////////////////////////
        getSido({
            commit
        }) {
            _axios
                .get(`/api/aptinfos/sido`)
                .then(({
                    data
                }) => {
                    // console.log(data);
                    commit("SET_SIDO_LIST", data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getGugun({
            commit
        }, sidoCode) {
            const params = {
                sido: sidoCode,
            };
            _axios
                .get(`/api/aptinfos/gugun`, {
                    params,
                })
                .then(({
                    data
                }) => {
                    // console.log(data);
                    commit("SET_GUGUN_LIST", data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getDong({
            commit
        }, gugunCode) {
            const params = {
                gugun: gugunCode,
            };
            _axios
                .get(`/api/aptinfos/dong`, {
                    params,
                })
                .then(({
                    data
                }) => {
                    // console.log(data);
                    commit("SET_DONG_LIST", data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getHouseList({
            commit
        }, dongCode) {
            // console.log(dongCode);
            const params = {
                LAWDCD: dongCode,
            };
            _axios
                .get(`/api/aptinfos/search`, {
                    params,
                })
                .then(({
                    data
                }) => {
                    // console.log(data);
                    commit("SET_HOUSE_LIST", data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getDealList({
            commit
        }, aptCode) {
            // console.log(dongCode);
            const params = {
                aptCode: aptCode,
            };
            _axios
                .get(`/api/aptinfos/aptCode`, {
                    params,
                })
                .then(({
                    data
                }) => {
                    // console.log(data);
                    commit("SET_DEAL_LIST", data);
                    console.log(data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getStateSido({
            commit
        }) {
            _axios.get(`/api/aptinfos/stats`)
                .then(data => {
                    commit("SET_STATS_SIDO_LIST", data);
                    console.log(data.data)
                });
        },
        setFavoriteApt({
            commit
        }, params) {
            _axios.post(`/favorite`,
                params,
            ).then(data => {
                commit("SET_FAVORITE", data);
            }).catch((error) => {
                console.log(error)
            });
        },
        getFavoriteApt({
            commit
        }, uid) {
            _axios.get(`/favorite/${uid}`).then(data => {
                commit("SET_FAVORITE", data);
            }).catch((error) => {
                console.log(error)
            });
        },
        removeFavoriteApt({
            commit
        }, params) {
            _axios.delete(`/favorite/${params.id}/${params.aptCode}`).then(data => {
                commit("SET_FAVORITE", data);
            }).catch((error) => {
                console.log(error)
            });
        },
        // detailHouse({ commit }, house) {
        //   // 나중에 house.일련번호를 이용하여 API 호출
        //   // console.log(commit, house);
        //   commit("SET_DETAIL_HOUSE", house);
        // },
        /////////////////////////////// House end /////////////////////////////////////
    },
};
export default houseStore;