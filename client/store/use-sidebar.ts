import { create } from "zustand";

interface SidebarStore {
    isCollapsed: boolean;
    onExpand: () => void;
    onCollapse: () => void;
}

export const useSidebar = create<SidebarStore>((set) => ({
    isCollapsed: false,
    onExpand: () => set({ isCollapsed: false }),
    onCollapse: () => set({ isCollapsed: true }),
}));
