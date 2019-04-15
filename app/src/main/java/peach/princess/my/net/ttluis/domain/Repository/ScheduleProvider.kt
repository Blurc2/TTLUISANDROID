package peach.princess.my.net.ttluis.domain.Repository

import io.reactivex.Scheduler

interface ScheduleProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun lo(): Scheduler
}