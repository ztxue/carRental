define(["vueRouter", "vue"], function (vueRouter, vue) {

    var asyncComp = function (componentName) {
        return function (resolve) {
            require([componentName], resolve);
        };
    };

    var router = new vueRouter({
        routes: [
            {
                path: "",
                name: "entry",
                redirect: "index"
            },
            {
                path: "/login",
                name: "login",
                component: asyncComp("rvue!view/auth/login"),
                meta: {
                    requiresAuth: false
                }
            },
            {
                path: "/register",
                name: "register",
                component: asyncComp("rvue!view/register/register"),
                meta: {
                    requiresAuth: false
                }
            },
            {
                path: "/index",
                name: "index",
                component: asyncComp("rvue!view/index"),
                meta: {
                    requiresAuth: true
                },
                children: [
                    {
                        path: "home",
                        name: "home",
                        component: asyncComp("rvue!view/home/home"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "userSys",
                        name: "userSys",
                        component: asyncComp("rvue!view/userSys/userSys"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "customer",
                        name: "customer",
                        component: asyncComp("rvue!view/customer/customer"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "carSys",
                        name: "carSys",
                        component: asyncComp("rvue!view/carSys/carSys"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "stock",
                        name: "stock",
                        component: asyncComp("rvue!view/carSys/stock/stock"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "service",
                        name: "service",
                        component: asyncComp("rvue!view/carSys/service/service"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "record",
                        name: "record",
                        component: asyncComp("rvue!view/record/record"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "finance",
                        name: "finance",
                        component: asyncComp("rvue!view/finance/finance"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                    {
                        path: "class",
                        name: "class",
                        component: asyncComp("rvue!view/carSys/class/class"),
                        meta: {
                            requiresAuth: true
                        }
                    },
                ]
            }
        ]
    });

    return router;
});
