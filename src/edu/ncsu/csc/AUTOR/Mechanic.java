package edu.ncsu.csc.AUTOR;

public class Mechanic {

    public Mechanic(long loginedUserId) {
        System.out.println("Access to Mechanic Landing page.");

        System.out.println("还没实现");
        //center();
    }

    // landing()
    // 列出用户可选项1. View Schedule
    // 2. Request TimeOff
    //3. Request Swap
    //4. Accept/Reject Swap
    //5. Logout ==> return;
    // 用scanner 获取用户input
    // 根据input index跳转

    //viewSchedule()
    //列出时间点和对应的技师----------------------------------------
    //这里暂时写不了，之前的设计有问题
    //看description file 的Car Service Scheduling部分
    //每天有九个时间点，十二点到一点不干活（no work is done this period）
    //技师每周工作时常<=50，每个服务由一名技师负责
    //记得查看商店是否在周六营业

    //requestTimeOff()
    //技师可以请假，需要经理批准 || 这一条在flow里没列出，暂不清楚要不要实现
    //先让用户输入indicated by week, day, time slot start and end slot ids
    //然后列出1.Send the request 发送请求
    //2.Go back

    //以下是额外分，有时间就写

    //requestSwap()
    //先让用户输入A: indicated by week, day, time slot start and end slot ids
    //然后B: Employee ID of a mechanic that is being requested for swap
    //还有C: Timeslot range of the requested mechanic that is of interest
    //然后列出1.Send the request 发送请求
    //2.Go back

    //acceptOrRejectSwap()
    //另一个技师可以看到其他技师发送的交换请求
    //先列出RequestID ==> 好像要新建TABLE才能实现
    //右边显示对应技师名和交换时间
    //然后列出1.Manage Swap Requests
    //2. Go bac

    //manageSwapRequests()
    //先让用户输入准备处理的RequestID
    //然后列出1.Accept swap
    //2. Reject swap
    //3. Go back
}
