requirejs.config({
    paths: {
        vue: 'https://cdn.bootcss.com/vue/2.5.13/vue.min',
        rvue: './lib/require-vuejs',
        vueCookies: './lib/vue-cookies',
        vueRouter: 'https://cdn.bootcss.com/vue-router/3.0.1/vue-router.min',
        vuex: 'https://cdn.bootcss.com/vuex/3.0.1/vuex.min',
        ELEMENT: 'https://cdn.bootcss.com/element-ui/2.0.11/index',
        VueI18n: 'https://cdn.bootcss.com/vue-i18n/7.4.0/vue-i18n.min',
        axios: 'https://cdn.bootcss.com/axios/0.17.1/axios.min',
        jquery: 'https://cdn.bootcss.com/jquery/3.2.1/jquery.min',
        bootstrap: 'https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min',
        SocketIO: 'https://cdn.bootcss.com/socket.io/2.0.4/socket.io.slim',
        moment: 'https://cdn.bootcss.com/moment.js/2.20.1/moment-with-locales.min',
        highCharts: './lib/highcharts',
        echarts: 'https://cdn.bootcss.com/echarts/4.0.2/echarts.min',
        china: './lib/china1',
        emoji: './lib/emoji/femoji',
        store: './vuex/store',
        WangEditor: './lib/wangEditor.min',
        Waterfall:'./lib/vue-waterfall.min'
    },
    map: {
        '*': {
            'css': 'https://cdn.bootcss.com/require-css/0.1.10/css.min.js'
        }
    },
    shim: {
        bootstrap: {
            deps: [
                'jquery',
                'css!https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css'
            ]
        },
        ELEMENT: {
            deps: [
                'vue',
                'css!https://cdn.bootcss.com/element-ui/2.0.11/theme-chalk/index.css'
            ]
        },
    },
});

require(["vue", "vueRouter", "ELEMENT", "./router/router", "vuex", "VueI18n", "moment", "axios", "vueCookies","store", "bootstrap"],
    function (Vue, vueRouter, ELEMENT, router, vuex, VueI18n, moment, axios, vueCookies, store) {
        Vue.use(vueRouter);
        Vue.use(ELEMENT);
        Vue.use(VueI18n);
        Vue.use(vueCookies);

        //获取浏览器语言设置
        var lang = navigator.language || navigator.userLanguage;
        moment.locale(lang);
        lang = lang ? lang.substr(0, 2) : 'zh';
        //国际化设置

        router.beforeEach((to, from, next) => {
            if (to.matched.some(record => record.meta.requiresAuth)) {
                var accessToken = sessionStorage.getItem("userId")
                console.log(accessToken);
                if (!accessToken) {
                    next({name: 'login'});
                } else {
                    // if (check(to.name)){
                    //     next();
                    // } else {
                    //     next({ name: 'login' });
                    // }
                    next();
                }
            } else {
                next();
            }
        });


        new Vue({
            router: router,
            store: store,

            // mounted: function () {
            //     //TODO 暂时禁止所有浏览器的回退操作，后面需根据需要做改动，比如从粉丝列表跳转到聊天页面就可以做回退操作
            //     history.pushState(null, null, location.href);
            //     // window.onpopstate = () => history.go(1);
            // }
        }).$mount('#app');
    });