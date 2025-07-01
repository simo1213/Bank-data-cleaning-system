<template>
    <div>
        <h1>{{title}}</h1>
        <span></span>
    </div>
</template>

<script>
export default {
    name:"Movie",//组件名称
    props:["title"],//定义这里之后外面就可以使用title了，相当于标签有了自定义属性，属性为title
    data:function()
    {
        return{
            
        }
    }
}
</script>