define(["vuex", "vue"], function (Vuex, Vue) {
    Vue.use(Vuex);
    const state = {
        pnList: [],
        show: false,
        updatePn: {},
        updateNews: [],
        flag: "",
        media: {},
        mediaSelectedStatus: false,
    };
    const mutations = {
        setPnList(state, pnList) {
            state.pnList = pnList;
        },
        setUpdatePn(state, pn) {
            state.updatePn = pn;
        },
        setUpdateNews(state, news) {
            state.updateNews = news;
        },
        setFlag(state, flag) {
            state.flag = flag;
        },
        setMedia(state, media) {
            state.media = media;
        },
        setMediaSelectedStatus(state, status) {
            state.mediaSelectedStatus = status;
        }
    };
    const actions = {
        savePnList({commit}, pnList) {
            commit('savePnList', pnList) // 提交到mutations中处理
        }
    };
// 获取状态信息
    const getter = {
        getPnList(state) {
            return state.pnList;
        },

    };
    return new Vuex.Store({
        state,
        getter,
        mutations,
        actions
    })
});