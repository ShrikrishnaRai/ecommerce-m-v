import request from '@/utils/request'

function getToken () {
    if (localStorage.getItem('user-token')) {
        return localStorage.getItem('user-token')
    }
}

export function getList (url) {
    if (getToken()) {
        return request({
            url: `/${url}`,
            headers: {
                Authorization: 'Bearer ' + getToken(),
            },
            method: 'get',
        })
    } else {
        return request({
            url: `/${url}`,
            method: 'get',
        })
    }
}

export function post (url, data) {
    if (getToken()) {
    return request({
        url: `/${url}`,
        headers: {
            Authorization: 'Bearer ' + getToken(),
        },
        method: 'post',
        data,
    })
    } else {
        return request({
            url: `/${url}`,
            method: 'post',
            data,
        })
    }
}
export function login (url, data) {
        return request({
            url: `/${url}`,
            method: 'post',
            data,
        })
}

export function remove (url, id) {
    alert(getToken())
    return request({
        url: `/${url}/${id}`,
        headers: {
            Authorization: 'Bearer ' + getToken(),
        },
        method: 'delete',
    })
}

export function update (url, id, data) {
    return request({
        url: `/${url}/${id}`,
        headers: {
            Authorization: 'Bearer ' + getToken(),
        },
        method: 'put',
        data,
    })
}

export function get (url, id) {
    if (getToken()) {
        return request({
            url: `/${url}/${id}`,
            headers: {
            Authorization: 'Bearer ' + getToken(),
            },
            method: 'get',
    })
    } else {
        return request({
            url: `/${url}/${id}`,
            method: 'get',
    })
}
}
export function getListWithId (url, id) {
    if (getToken()) {
        return request({
            url: `/${url}/${id}`,
            headers: {
            Authorization: 'Bearer ' + getToken(),
            },
            method: 'get',
    })
    } else {
        return request({
            url: `/${url}/${id}`,
            method: 'get',
    })
}
}
